package com.harvest.oms.client.handler.order.section;

import com.harvest.oms.client.handler.order.OrderSectionHandler;
import com.harvest.oms.client.order.OrderReadClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.OrderItemDO;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/1 6:24 PM
 * @Description: TODO
 **/
@Order(1)
@Component
public class OrderItemSectionHandler implements OrderSectionHandler {

    @Autowired
    private OrderReadClient orderReadClient;

    @Override
    public void fill(Long companyId, OrderInfoDO entity) {

    }

    @Override
    public void batchFill(Long companyId, Collection<OrderInfoDO> orders) {
        List<Long> orderIds = orders.stream().map(OrderInfoDO::getOrderId).collect(Collectors.toList());
        Map<Long, List<OrderItemDO>> orderIdItemMap = orderReadClient.mapOrderItemByOrderIds(companyId, orderIds);
        if (MapUtils.isEmpty(orderIdItemMap)) {
            return;
        }

        orders.forEach(orderInfoDO -> {
            Long orderId = orderInfoDO.getOrderId();
            Collection<OrderItemDO> orderItems = orderIdItemMap.get(orderId);
            orderInfoDO.setOrderItems(orderItems);
        });
    }
}
