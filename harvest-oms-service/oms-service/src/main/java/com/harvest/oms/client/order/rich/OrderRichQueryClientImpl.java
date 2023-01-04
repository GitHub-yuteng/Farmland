package com.harvest.oms.client.order.rich;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.domain.Page;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.client.handler.order.OrderSectionHandler;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.client.order.rich.OrderRichQueryRepositoryClient;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
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
     * 如果订单数超过200则采用并发查询，提高查询效率
     */
    private final static int ORDER_NUMS = 200;

    /**
     * 订单扩展信息查询线程池
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
    private List<OrderSectionHandler> orderSectionHandlers;


    @Override
    public Page<OrderInfoDO> pageQueryOrderRich(Long companyId, PageOrderConditionQuery pageOrderConditionQuery) {
        Page<OrderSimplePO> orderSimplePage = orderRichQueryRepositoryClient.pageQueryOrderRich(companyId, pageOrderConditionQuery);
        Page<OrderInfoDO> result = this.convent(orderSimplePage);
        this.sectionBatchFill(companyId, result.getData());
        return result;
    }

    private void sectionBatchFill(Long companyId, Collection<OrderInfoDO> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }

        if (orders.size() >= ORDER_NUMS) {
            try {
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
                LOGGER.error("并发补充订单信息失败", e);
            }
        } else {
            orderSectionHandlers.forEach(section -> section.batchFill(companyId, orders));
        }
    }

    /**
     * 转换DO
     *
     * @param orderSimplePage
     * @return
     */
    private Page<OrderInfoDO> convent(Page<OrderSimplePO> orderSimplePage) {
        Collection<OrderSimplePO> orderSimpleList = orderSimplePage.getData();
        List<OrderInfoDO> orderInfoList = orderSimpleList.stream().map(orderSimplePO -> {
            OrderInfoDO orderInfoDO = new OrderInfoDO();
            BeanUtils.copyProperties(orderSimplePO, orderInfoDO);
            return orderInfoDO;
        }).collect(Collectors.toList());

        return new Page<>(
                orderSimplePage.getPageNo(),
                orderSimplePage.getPageSize(),
                orderInfoList,
                orderSimplePage.getCount()
        );

    }
}
