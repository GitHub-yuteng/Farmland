package com.harvest.oms.service.order.handler.delivery;

import com.harvest.core.enums.oms.OrderStatusEnum;
import com.harvest.core.service.mq.ProducerMessageService;
import com.harvest.oms.client.order.OrderWriteClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.service.order.event.OrderEventPublisher;
import com.harvest.oms.service.order.processor.OrderDeliveryProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 3:44 PM
 * @Description: 订单审核执行器
 **/
@Component
public class OrderDeliveryExecutor implements OrderDeliveryProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderDeliveryExecutor.class);

    @Autowired
    private OrderWriteClient orderWriteClient;

    @Autowired
    private ProducerMessageService producerMessageService;

    @Autowired
    private OrderEventPublisher orderEventPublisher;

    public void exec(Long companyId, OrderInfoDO order) {
        this.execute(companyId, order);
    }

    /**
     * 订单发货前置校验-报错处理
     *
     * @param companyId
     * @param order
     * @return
     */
    @Override
    public void check(Long companyId, OrderInfoDO order) {

    }

    /**
     * 订单发货前置处理-处理参数等
     *
     * @param companyId
     * @param order
     * @return
     */
    @Override
    public void beforeDelivery(Long companyId, OrderInfoDO order) {

    }

    /**
     * 业务处理过程
     *
     * @param companyId
     * @param order
     */
    @Override
    public void processDelivery(Long companyId, OrderInfoDO order) {
        order.setOrderStatus(OrderStatusEnum.SHIPPED);
        orderWriteClient.updateOrderStatus(companyId, order);
    }

    /**
     * 订单发货后置处理
     *
     * @param companyId
     * @param order
     */
    @Override
    public void afterDelivery(Long companyId, OrderInfoDO order) {

    }
}
