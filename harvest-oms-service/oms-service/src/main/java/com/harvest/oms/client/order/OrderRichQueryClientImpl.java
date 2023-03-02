package com.harvest.oms.client.order;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.harvest.core.annotation.Monitor;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.domain.Page;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.utils.JsonUtils;
import com.harvest.core.utils.PartitionUtils;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.OrderItemDO;
import com.harvest.oms.repository.client.order.OrderRichQueryRepositoryClient;
import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.service.order.convertor.OrderConvertor;
import com.harvest.oms.service.order.handler.feature.company.OrderCompanyFeatureHandler;
import com.harvest.oms.service.order.handler.feature.logistics.OrderLogisticsFeatureHandler;
import com.harvest.oms.service.order.handler.feature.platform.OrderPlatformFeatureHandler;
import com.harvest.oms.service.order.handler.section.OrderSectionHandler;
import com.harvest.oms.service.order.verifier.OrderPermissionsVerifier;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 4:37 PM
 * @Description: 订单丰富查询服务
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_RICH)
public class OrderRichQueryClientImpl implements OrderRichQueryClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderRichQueryClientImpl.class);

    /**
     * 查询超时警告
     */
    private final static long TIME_OUT = 1000L * 3;

    /**
     * 如果订单数超过200则采用并发查询，提高查询效率
     */
    private final static int ORDER_NUMS = 200;

    /**
     * 订单信息查询线程池
     */
    private final static Executor OMS_SECTION_READ_EXECUTOR = new ThreadPoolExecutor(100, 100, 2000, TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(),
            new ThreadFactoryBuilder()
                    .setNameFormat("harvest-oms-section-reading-%d")
                    .setUncaughtExceptionHandler((thread, e) -> LOGGER.error("ThreadPool:{} 发生异常", thread, e))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Autowired
    private OrderRichQueryRepositoryClient orderRichQueryRepositoryClient;

    @Autowired
    private OrderReadClient orderReadClient;

    @Autowired
    private OrderConvertor orderConvertor;

    @Autowired
    private List<OrderPermissionsVerifier> orderPermissionsVerifiers;

    @Autowired
    private List<OrderSectionHandler> orderSectionHandlers;

    @Autowired(required = false)
    private List<OrderCompanyFeatureHandler> orderCompanyFeatureHandlers;

    @Autowired(required = false)
    private List<OrderPlatformFeatureHandler> orderPlatformFeatureHandlers;

    @Autowired(required = false)
    private List<OrderLogisticsFeatureHandler> orderLogisticsFeatureHandlers;


    @Override
    public OrderInfoDO getOrder(Long companyId, Long orderId) {
        PageOrderConditionQuery condition = new PageOrderConditionQuery();
        condition.setOrderIds(Collections.singletonList(orderId));
        Collection<OrderInfoDO> data = this.listQueryOrder(companyId, condition);
        if (CollectionUtils.isEmpty(data)) {
            LOGGER.error("OrderRichQueryClientImpl#getOrder#订单不存在, companyId:{}, orderId:{}", companyId, orderId);
            throw new StandardRuntimeException("订单不存在！");
        }
        return data.iterator().next();
    }

    @Override
    public Collection<OrderInfoDO> listQueryOrder(Long companyId, PageOrderConditionQuery condition) {
        Page<OrderInfoDO> page = this.pageQueryOrder(companyId, condition);
        if (CollectionUtils.isEmpty(page.getData())) {
            return Collections.emptyList();
        }
        return page.getData();
    }

    @Monitor(efficiencyWatch = 1000)
    @Override
    public Page<OrderInfoDO> pageQueryOrder(Long companyId, PageOrderConditionQuery condition) {

        StopWatch stopWatch = new StopWatch("订单分页查询");

        stopWatch.start("权限校验");
        this.verification(companyId, condition);
        stopWatch.stop();

        stopWatch.start("订单信息查询");
        Page<OrderSimplePO> page = orderRichQueryRepositoryClient.pageQueryOrderRich(companyId, condition);
        stopWatch.stop();

        stopWatch.start("领域模型转换");
        Collection<OrderInfoDO> data = orderConvertor.OrderInfoDO(page.getData());
        stopWatch.stop();

        stopWatch.start("订单明细信息填充");
        this.orderItemBatchFill(companyId, data);
        stopWatch.stop();

        if (stopWatch.getTotalTimeMillis() > TIME_OUT) {
            LOGGER.warn("OrderRichQueryClientImpl#pageQueryOrder#订单查询超时, companyId:{}, condition:{}\nstopWatch:{}", companyId, JsonUtils.object2Json(condition), stopWatch.prettyPrint());
        }

        return Page.build(condition.getPageNo(), condition.getPageSize(), data, page.getCount());
    }

    @Override
    public OrderInfoDO getOrderRich(Long companyId, Long orderId) {
        PageOrderConditionQuery condition = new PageOrderConditionQuery();
        condition.setOrderIds(Collections.singletonList(orderId));
        Collection<OrderInfoDO> data = this.listQueryOrderRich(companyId, condition);
        if (CollectionUtils.isEmpty(data)) {
            LOGGER.error("OrderRichQueryClientImpl#getOrderRich#订单不存在, companyId:{}, orderId:{}", companyId, orderId);
            throw new StandardRuntimeException("订单不存在！");
        }
        return data.iterator().next();
    }

    @Override
    public Collection<OrderInfoDO> listQueryOrderRich(Long companyId, PageOrderConditionQuery condition) {
        Page<OrderInfoDO> page = this.pageQueryOrderRich(companyId, condition);
        if (CollectionUtils.isEmpty(page.getData())) {
            return Collections.emptyList();
        }
        return page.getData();
    }

    @Monitor(efficiencyWatch = 2000)
    @Override
    public Page<OrderInfoDO> pageQueryOrderRich(Long companyId, PageOrderConditionQuery condition) {

        StopWatch stopWatch = new StopWatch("订单丰富查询");

        stopWatch.start("订单信息查询");
        Page<OrderInfoDO> page = this.pageQueryOrder(companyId, condition);
        stopWatch.stop();

        Collection<OrderInfoDO> data = page.getData();

        stopWatch.start("领域模型信息填充");
        this.sectionBatchFill(companyId, data);
        stopWatch.stop();

        stopWatch.start("平台订单特性处理");
        this.platformFeatureBatchHandler(companyId, data);
        stopWatch.stop();

        stopWatch.start("公司订单特性处理");
        this.companyFeatureBatchHandler(companyId, data);
        stopWatch.stop();

        stopWatch.start("物流订单特性处理");
        this.logisticsFeatureBatchHandler(companyId, data);
        stopWatch.stop();

        if (stopWatch.getTotalTimeMillis() > TIME_OUT) {
            LOGGER.warn("OrderRichQueryClientImpl#pageQueryOrderRich#订单丰富查询, companyId:{}, condition:{}\nstopWatch:{}", companyId, JsonUtils.object2Json(condition), stopWatch.prettyPrint());
        }

        return Page.build(condition.getPageNo(), condition.getPageSize(), data, page.getCount());
    }

    /**
     * 权限校验
     *
     * @param companyId
     * @param condition
     */
    private void verification(Long companyId, PageOrderConditionQuery condition) {
        orderPermissionsVerifiers.forEach(v -> v.check(companyId, condition));
    }

    /**
     * 订单明细信息填充 ｜ 其他领域模型可能需要明细数据 单独摘出填充（防止多线程查询填充没有对应数据）
     *
     * @param companyId
     * @param orders
     */
    private void orderItemBatchFill(Long companyId, Collection<OrderInfoDO> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }
        List<Long> orderIds = orders.stream().map(OrderInfoDO::getOrderId).collect(Collectors.toList());
        Map<Long, List<OrderItemSimplePO>> orderIdItemMap = this.partitionBatch(companyId, orderIds);
        if (MapUtils.isEmpty(orderIdItemMap)) {
            return;
        }
        orders.forEach(orderInfoDO -> {
            Long orderId = orderInfoDO.getOrderId();
            Collection<OrderItemSimplePO> orderItemSimplePOList = orderIdItemMap.get(orderId);
            orderInfoDO.setOrderItems(orderItemSimplePOList.stream()
                    .map(orderItemSimplePO -> {
                        OrderItemDO orderItemDO = new OrderItemDO();
                        BeanUtils.copyProperties(orderItemSimplePO, orderItemDO);
                        return orderItemDO;
                    }).collect(Collectors.toList())
            );
        });
    }

    /**
     * 如果订单Ids过多 分区分批查询优化 只查询简要信息
     *
     * @param companyId
     * @param orderIds
     * @return
     */
    private Map<Long, List<OrderItemSimplePO>> partitionBatch(Long companyId, List<Long> orderIds) {
        // extension 大字段 影响IO 在丰富查询时考虑查询效率则延迟查出，判断存在对应的 tagValue 单独取对应的 扩展信息进行处理
        return PartitionUtils.partitionMapExecute(orderIds, ORDER_NUMS, f -> orderReadClient.mapOrderItemByOrderIds(companyId, f));
    }

    /**
     * 订单领域模型部分信息填充器
     *
     * @param companyId
     * @param orders
     */
    private void sectionBatchFill(Long companyId, Collection<OrderInfoDO> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }

        if (orders.size() >= ORDER_NUMS) {
            try {
                // 订单量过多 多线程填充订单其他领域信息
                CompletableFuture<?>[] futures = new CompletableFuture<?>[orderSectionHandlers.size()];
                for (int i = 0; i < orderSectionHandlers.size(); i++) {
                    int finalI = i;
                    futures[i] = CompletableFuture.runAsync(
                            () -> orderSectionHandlers.get(finalI).batchFill(companyId, orders),
                            OMS_SECTION_READ_EXECUTOR
                    );
                }
                CompletableFuture.allOf(futures).get();
            } catch (Exception e) {
                LOGGER.error("OrderRichQueryClientImpl#sectionBatchFill#并发补充订单领域信息失败!", e);
            }
        } else {
            orderSectionHandlers.forEach(handler -> handler.batchFill(companyId, orders));
        }
    }

    /**
     * 物流订单特性处理
     *
     * @param companyId
     * @param orders
     */
    private void logisticsFeatureBatchHandler(Long companyId, Collection<OrderInfoDO> orders) {
        if (CollectionUtils.isEmpty(orders) || CollectionUtils.isEmpty(orderLogisticsFeatureHandlers)) {
            return;
        }

        long count = orders.stream().map(OrderInfoDO::getLogisticsEnum).distinct().count();
        if (count >= DEFAULT_10) {
            try {
                // 本次查询平台数量统计 超过10个平台使用并发处理
                CompletableFuture<?>[] futures = new CompletableFuture<?>[orderLogisticsFeatureHandlers.size()];
                for (int i = 0; i < orderLogisticsFeatureHandlers.size(); i++) {
                    int finalI = i;
                    futures[i] = CompletableFuture.runAsync(
                            () -> orderLogisticsFeatureHandlers.get(finalI).batchFeatureFill(companyId, orders),
                            OMS_SECTION_READ_EXECUTOR
                    );
                }
                CompletableFuture.allOf(futures).get();
            } catch (Exception e) {
                LOGGER.error("OrderRichQueryClientImpl#LogisticsFeatureHandlers#并发处理物流订单特性处理失败!", e);
            }
        } else {
            orderLogisticsFeatureHandlers.forEach(handler -> handler.batchFeatureFill(companyId, orders));
        }
    }

    /**
     * 平台订单特性处理
     *
     * @param companyId
     * @param orders
     */
    private void platformFeatureBatchHandler(Long companyId, Collection<OrderInfoDO> orders) {
        if (CollectionUtils.isEmpty(orders) || CollectionUtils.isEmpty(orderPlatformFeatureHandlers)) {
            return;
        }

        long count = orders.stream().map(OrderInfoDO::getOrderSource).distinct().count();
        if (count >= DEFAULT_10) {
            try {
                // 本次查询平台数量统计 超过10个平台使用并发处理
                CompletableFuture<?>[] futures = new CompletableFuture<?>[orderPlatformFeatureHandlers.size()];
                for (int i = 0; i < orderPlatformFeatureHandlers.size(); i++) {
                    int finalI = i;
                    futures[i] = CompletableFuture.runAsync(
                            () -> orderPlatformFeatureHandlers.get(finalI).batchFeatureFill(companyId, orders),
                            OMS_SECTION_READ_EXECUTOR
                    );
                }
                CompletableFuture.allOf(futures).get();
            } catch (Exception e) {
                LOGGER.error("OrderRichQueryClientImpl#platformFeatureBatchHandler#并发处理平台订单特性处理失败!", e);
            }
        } else {
            orderPlatformFeatureHandlers.forEach(handler -> handler.batchFeatureFill(companyId, orders));
        }
    }

    /**
     * 公司订单特性处理
     *
     * @param companyId
     * @param orders
     */
    private void companyFeatureBatchHandler(Long companyId, Collection<OrderInfoDO> orders) {
        if (CollectionUtils.isEmpty(orders) || CollectionUtils.isEmpty(orderCompanyFeatureHandlers)) {
            return;
        }
        orderCompanyFeatureHandlers.forEach(feature -> {
            if (feature.match(companyId)) {
                feature.batchFeatureFill(companyId, orders);
            }
        });
    }


    @Override
    public Collection<OrderItemDO> queryOrderItemsRich(Long companyId, Long orderId) {
        Collection<OrderItemSimplePO> orderItemSimplePOList = orderReadClient.listOrderItemByOrderId(companyId, orderId);
        Collection<OrderItemDO> orderItemDOList = this.convert(orderItemSimplePOList);
        return orderItemDOList;
    }

    /**
     * 转换订单明细领域模型
     *
     * @param collection
     * @return
     */
    private Collection<OrderItemDO> convert(Collection<OrderItemSimplePO> collection) {
        return collection.stream().map(item -> {
            OrderItemDO orderItemDO = new OrderItemDO();
            BeanUtils.copyProperties(item, orderItemDO);
            return orderItemDO;
        }).collect(Collectors.toList());
    }

}
