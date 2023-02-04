package com.harvest.oms.repository.client.order;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.harvest.core.domain.Page;
import com.harvest.core.domain.range.date.DataTimeRange;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.utils.FieldUtils;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.convert.OrderOptimizeQueryConvertor;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.handler.order.OrderSectionRepositoryHandler;
import com.harvest.oms.repository.mapper.order.rich.OrderRichConditionQueryMapper;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.repository.query.order.pack.OrderNoQuery;
import com.harvest.oms.repository.query.order.pack.OrderRemarkQuery;
import com.harvest.oms.repository.query.order.pack.OrderTimeQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.*;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 4:48 PM
 * @Description: 订单丰富查询服务
 **/
@HarvestService(value = HarvestOmsRepositoryApplications.SERVICE_NAME, path = HarvestOmsRepositoryApplications.Path.ORDER_RICH)
public class OrderRichQueryRepositoryClientImpl implements OrderRichQueryRepositoryClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderRichQueryRepositoryClientImpl.class);

    /**
     * 查询超时时常警告
     */
    private final static long TIME_OUT = 1000L;

    /**
     * 如果订单数超过200则采用并发查询，提高查询效率
     */
    private final static int ORDER_NUMS = 200;

    /**
     * 订单扩展信息查询线程池
     */
    private final static Executor OMS_SECTION_READ_EXECUTOR = new ThreadPoolExecutor(100, 100, 2000, TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(),
            new ThreadFactoryBuilder()
                    .setNameFormat("harvest-oms-section-repository-reading-%d")
                    .setUncaughtExceptionHandler((thread, e) -> LOGGER.error("ThreadPool:{} 发生异常", thread, e))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Autowired
    private OrderRichConditionQueryMapper orderRichConditionQueryMapper;

    @Autowired
    private List<OrderSectionRepositoryHandler<?>> orderSectionRepositoryHandlers;

    @Autowired
    private List<OrderOptimizeQueryConvertor> orderOptimizeQueryConvertors;


    @Override
    public Page<OrderSimplePO> pageQueryOrderRich(Long companyId, PageOrderConditionQuery condition) {
        Page<OrderSimplePO> page = new Page<>(condition.getPageNo(), condition.getPageSize());

        StopWatch stopWatch = new StopWatch();

        stopWatch.start("简化查询结构");
        Map<String, Object> paramsMap = this.conventParams(companyId, condition);
        stopWatch.stop();

        stopWatch.start("拆分查询");
        Optional<OrderOptimizeQueryConvertor> optimizeConvertor = orderOptimizeQueryConvertors.stream()
                .filter(convertor -> convertor.process(companyId, condition, paramsMap))
                .findAny();
        stopWatch.stop();

        List<Long> orderIds = (List<Long>) paramsMap.get(FieldUtils.getFieldName(PageOrderConditionQuery::getOrderIds));
        if (CollectionUtils.isNotEmpty(orderIds) && orderIds.contains(0L)) {
            LOGGER.warn("Repository#Rich#订单拆分查询为空, companyId:{}, optimizeConvertor:{}, \nstopWatch:{}", companyId, JsonUtils.object2Json(optimizeConvertor.getClass().getSimpleName()), stopWatch.prettyPrint());
            return page;
        }

        stopWatch.start("订单总数查询");
        Long count = orderRichConditionQueryMapper.countQueryOrderWithRichCondition(paramsMap);
        stopWatch.stop();

        if (count == 0) {
            LOGGER.warn("Rich#订单查询为空, companyId:{}, condition:{}, \nstopWatch:{}", companyId, JsonUtils.object2Json(condition), stopWatch.prettyPrint());
            return page;
        }

        // 总数
        page.setCount(count);

        stopWatch.start("订单信息查询");
        Collection<OrderSimplePO> orders = orderRichConditionQueryMapper.pageQueryOrderWithRichCondition(paramsMap);
        page.setData(orders);
        stopWatch.stop();

        stopWatch.start("补充信息填充");
        this.sectionBatchFill(companyId, orders);
        stopWatch.stop();

        if (stopWatch.getTotalTimeMillis() > TIME_OUT) {
            LOGGER.warn("Rich#订单查询超时, companyId:{}, condition:{}, \nstopWatch:{}", companyId, JsonUtils.object2Json(condition), stopWatch.prettyPrint());
        }

        return page;
    }

    /**
     * 信息填充
     *
     * @param companyId
     * @param orders
     */
    private void sectionBatchFill(Long companyId, Collection<OrderSimplePO> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }

        if (orders.size() >= ORDER_NUMS) {
            try {
                CompletableFuture<?>[] futures = new CompletableFuture<?>[orderSectionRepositoryHandlers.size()];
                for (int i = 0; i < orderSectionRepositoryHandlers.size(); i++) {
                    int finalI = i;
                    futures[i] = CompletableFuture.runAsync(
                            () -> orderSectionRepositoryHandlers.get(finalI).batchFill(companyId, orders),
                            OMS_SECTION_READ_EXECUTOR
                    );
                }
                CompletableFuture.allOf(futures).get();
            } catch (Exception e) {
                LOGGER.error("并发补充订单信息失败", e);
            }
        } else {
            orderSectionRepositoryHandlers.forEach(section -> section.batchFill(companyId, orders));
        }
    }

    @Override
    public Collection<OrderSimplePO> listQueryOrderRich(Long companyId, PageOrderConditionQuery condition) {
        Page<OrderSimplePO> page = this.pageQueryOrderRich(companyId, condition);
        if (CollectionUtils.isEmpty(page.getData())) {
            return Collections.emptyList();
        }
        return page.getData();
    }

    /**
     * 简化查询结构
     *
     * @param companyId
     * @param condition
     * @return
     */
    private Map<String, Object> conventParams(Long companyId, PageOrderConditionQuery condition) {
        Map<String, Object> paramsMap = Maps.newHashMap();

        paramsMap.put("companyId", companyId);
        paramsMap.put("from", condition.getFrom());
        paramsMap.put("limit", condition.getLimit());

        if (CollectionUtils.isNotEmpty(condition.getOrderIds())) {
            paramsMap.put("orderIds", condition.getOrderIds());
        }

        if (StringUtils.isNotEmpty(condition.getOrderNo())) {
            paramsMap.put("orderNo", condition.getOrderNo());
        }

        if (CollectionUtils.isNotEmpty(condition.getShopIds())) {
            paramsMap.put("shopIds", condition.getShopIds());
        }

        if (CollectionUtils.isNotEmpty(condition.getStatuses())) {
            paramsMap.put("statuses", condition.getStatuses());
        }

        if (CollectionUtils.isNotEmpty(condition.getIncludeTags())) {
            paramsMap.put("includeTags", condition.getIncludeTags());
        }

        if (CollectionUtils.isNotEmpty(condition.getExcludeTags())) {
            paramsMap.put("excludeTags", condition.getExcludeTags());
        }

        //TODO 订单时间
        OrderTimeQuery orderTime = condition.getOrderTime();
        if (Objects.nonNull(orderTime)) {
            DataTimeRange createdTime = orderTime.getCreatedTime();
            if (Objects.nonNull(createdTime)) {
                paramsMap.put("createdTimeMin", createdTime.getMin());
                paramsMap.put("createdTimeMax", createdTime.getMax());
            }

            DataTimeRange paidTime = orderTime.getPaidTime();
            if (Objects.nonNull(createdTime)) {
                paramsMap.put("paidTimeMin", createdTime.getMin());
                paramsMap.put("paidTimeMax", createdTime.getMax());
            }

            // 后续补充
        }

        //TODO 订单备注
        OrderRemarkQuery orderRemark = condition.getOrderRemark();
        if (Objects.nonNull(orderRemark)) {
            OrderRemarkQuery.OrderRemark buyerRemark = orderRemark.getBuyer();
            if (Objects.nonNull(buyerRemark) && !buyerRemark.isEmpty()) {
                paramsMap.put("buyerRemark", buyerRemark.getRemark());
            }
            // 后续补充
        }

        //TODO 订单级别单号查询
        OrderNoQuery nos = condition.getNos();
        if (Objects.nonNull(nos)) {
            Collection<String> orderNos = nos.getOrderNos();
            if (CollectionUtils.isNotEmpty(orderNos)) {
                paramsMap.put("orderNos", orderNos);
            }
            // 后续补充
        }

        if (Objects.nonNull(condition.getAbnormal())) {
            paramsMap.put("abnormal", condition.getAbnormal());
        }

        return paramsMap;
    }
}
