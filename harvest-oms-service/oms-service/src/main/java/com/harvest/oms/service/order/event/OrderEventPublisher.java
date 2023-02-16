package com.harvest.oms.service.order.event;

import com.harvest.core.batch.BatchId;
import com.harvest.core.utils.ActuatorUtils;
import com.harvest.oms.client.order.OrderReadClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.service.order.listener.OrderEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: Alodi
 * @Date: 2022/12/12 9:27 PM
 * @Description: 订单事件发布
 **/
@Component
public class OrderEventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventPublisher.class);

    @Autowired(required = false)
    private List<OrderEventListener> orderEventListeners;

    @Autowired
    private OrderReadClient orderReadClient;

    /**
     * 异步执行监听事件
     *
     * @param companyId
     * @param orderId
     * @param event
     */
    public void publishAsync(Long companyId, Long orderId, OrderEventEnum event) {
        ActuatorUtils.parallelVoidFailAllowBatchExecute(Stream.of(orderId).map(BatchId::build).collect(Collectors.toList()),
                id -> this.publish(companyId, orderId, event)
        );
    }

    /**
     * 同步执行监听事件
     */
    public void publish(Long companyId, Long orderId, OrderEventEnum type) {
        if (companyId == 0L || orderId == 0L || type == null) {
            return;
        }
        /*内存中的可能有脏数据，统一从数据库捞一遍数据*/
        OrderInfoDO order = orderReadClient.get(companyId, orderId);
        this.publish(companyId, order, type);
    }

    public void publish(long companyId, OrderInfoDO order, OrderEventEnum type) {
        this.doPublish(companyId, order, type);
    }

    /**
     * 执行发布
     *
     * @param companyId
     * @param order
     * @param event
     */
    private void doPublish(long companyId, OrderInfoDO order, OrderEventEnum event) {
        orderEventListeners.forEach(listener -> {
            switch (event) {
                case CREATED:
                    listener.created(companyId, order);
                    break;
                case PAID:
                    listener.paid(companyId, order);
                    break;
                case AUDIT:
                    listener.audit(companyId, order);
                    break;
                case RETURN_AUDIT:
                    listener.returnAudit(companyId, order);
                    break;
                case DECLARE:
                    listener.declare(companyId, order);
                    break;
                case DELIVERY:
                    listener.delivery(companyId, order);
                    break;
                case CLOSE:
                    listener.close(companyId, order);
                    break;
                default:
                    break;
            }
        });
    }


}
