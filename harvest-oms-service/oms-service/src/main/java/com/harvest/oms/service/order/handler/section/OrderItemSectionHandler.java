package com.harvest.oms.service.order.handler.section;

import com.harvest.core.utils.QueryUtils;
import com.harvest.oms.client.order.OrderReadClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.OrderItemDO;
import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import com.harvest.oms.service.order.handler.OrderSectionHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/1 6:24 PM
 * @Description:
 * {@link OrderSectionHandler.Order#A}
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class OrderItemSectionHandler implements OrderSectionHandler {

    private final static Integer ORDER_ITEM_PARTITION_SIZE = 200;

    @Autowired
    private OrderReadClient orderReadClient;

    @Override
    public void fill(Long companyId, OrderInfoDO order) {
        this.batchFill(companyId, Collections.singleton(order));
    }

    @Override
    public void batchFill(Long companyId, Collection<OrderInfoDO> orders) {
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
     * ????????????Ids?????? ???????????????????????? ?????????????????????
     *
     * @param companyId
     * @param orderIds
     * @return
     */
    private Map<Long, List<OrderItemSimplePO>> partitionBatch(Long companyId, List<Long> orderIds) {
        // extension ????????? ??????IO ??????????????????????????????????????????????????????????????????????????? tagValue ?????????????????? ????????????????????????
        return QueryUtils.partitionMapExecute(orderIds, ORDER_ITEM_PARTITION_SIZE, f -> orderReadClient.mapOrderItemByOrderIds(companyId, f));
    }
}
