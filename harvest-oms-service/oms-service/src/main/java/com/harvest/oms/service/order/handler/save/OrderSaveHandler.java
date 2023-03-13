package com.harvest.oms.service.order.handler.save;

import com.google.common.collect.Lists;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.repository.client.order.OrderWriteRepositoryClient;
import com.harvest.oms.service.order.event.OrderEventPublisher;
import com.harvest.oms.service.order.strategy.OrderRuleStrategy;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/2/23 11:53 AM
 * @Description: TODO
 **/
@Component
public class OrderSaveHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSaveHandler.class);

    @Autowired
    private OrderWriteRepositoryClient orderWriteRepositoryClient;
    @Autowired
    private List<OrderRuleStrategy> orderRuleStrategys;
    @Autowired
    private OrderEventPublisher orderEventPublisher;

    public void save(Long companyId, OrderInfoDO order) {

        StopWatch stopWatch = new StopWatch("新增订单效率监测");

        stopWatch.start("保存订单");
        orderWriteRepositoryClient.insert(companyId, order);
        stopWatch.stop();

        stopWatch.start("保存订单明细");
        orderWriteRepositoryClient.insertItems(companyId, Lists.newArrayList(order.getOrderItems()));
        stopWatch.stop();

        stopWatch.start("执行策略");
        this.executeRuleStrategy(companyId, order);
        stopWatch.stop();

        stopWatch.start("发布订单创建事件");
        orderEventPublisher.publish(companyId, order.getOrderId(), OrderEventEnum.CREATED);
        stopWatch.stop();


    }

    private void executeRuleStrategy(long companyId, OrderInfoDO order) {
        if (CollectionUtils.isEmpty(orderRuleStrategys)) {
            return;
        }
        StopWatch stopWatch = new StopWatch("执行策略检测");
        orderRuleStrategys.forEach(r -> {
            stopWatch.start(r.getClass().getSimpleName());
            try {
                //考虑 异步执行策略
                r.execute(companyId, order);
            } catch (Exception e) {
                if (e instanceof StandardRuntimeException) {
                    LOGGER.info(String.format("执行订单策略失败:执行器 %s ,失败原因: %s", r.getClass().getSimpleName(), e.getMessage()), e);
                } else {
                    LOGGER.error(String.format("执行订单策略失败:执行器 %s ,失败原因: %s", r.getClass().getSimpleName(), e.getMessage()), e);
                }
            }
            stopWatch.stop();
        });
        if (stopWatch.getTotalTimeMillis() >= 1000) {
            LOGGER.info(stopWatch.prettyPrint());
        }
    }
}
