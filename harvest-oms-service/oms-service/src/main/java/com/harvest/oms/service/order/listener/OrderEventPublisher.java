package com.harvest.oms.service.order.listener;

import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.client.order.OrderReadClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    private void doPublish(long companyId, OrderInfoDO order, OrderEventEnum type) {
        orderEventListeners.forEach(listener -> {
            switch (type) {
                case CREATED:
                    listener.created(companyId, order);
                    break;
                case PAID:
                case AUDIT:
                case RETURN_AUDIT:
                case CLOSE:
                    break;
                default:
                    break;
            }
        });
    }

}
