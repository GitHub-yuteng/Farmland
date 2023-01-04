package com.harvest.oms.client.order.read;

import com.google.common.collect.Maps;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.client.order.OrderReadClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.OrderItemDO;
import com.harvest.oms.repository.client.order.OrderReadRepositoryClient;
import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 10:24 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_READ)
public class OrderReadClientImpl implements OrderReadClient {

    @Autowired
    private OrderReadRepositoryClient orderReadRepositoryClient;

    @Override
    public OrderInfoDO get(Long companyId, Long orderId) {
        OrderSimplePO orderSimplePO = orderReadRepositoryClient.get(companyId, orderId);
        OrderInfoDO orderInfoDO = new OrderInfoDO();
        BeanUtils.copyProperties(orderSimplePO, orderInfoDO);
        return orderInfoDO;
    }

    @Override
    public Collection<OrderInfoDO> listOrderByOrderIds(Long companyId, List<Long> orderIds) {
        return null;
    }

    @Override
    public Collection<OrderItemDO> listOrderItemByOrderIds(Long companyId, Long orderId) {
        Collection<OrderItemSimplePO> orderItemSimplePOList = orderReadRepositoryClient.listOrderItemByOrderIds(companyId, Collections.singletonList(orderId));
        return orderItemSimplePOList.stream().map(orderSimplePO -> {
            OrderItemDO orderItemDO = new OrderItemDO();
            BeanUtils.copyProperties(orderSimplePO, orderItemDO);
            return orderItemDO;
        }).collect(Collectors.toList());
    }

    @Override
    public Map<Long, List<OrderItemDO>> mapOrderItemByOrderIds(Long companyId, List<Long> orderIds) {
        Collection<OrderItemSimplePO> orderItemSimplePOList = orderReadRepositoryClient.listOrderItemByOrderIds(companyId, orderIds);
        if (CollectionUtils.isEmpty(orderItemSimplePOList)) {
            return Maps.newHashMap();
        }
        return orderItemSimplePOList.stream().map(orderSimplePO -> {
            OrderItemDO orderItemDO = new OrderItemDO();
            BeanUtils.copyProperties(orderSimplePO, orderItemDO);
            return orderItemDO;
        }).collect(Collectors.groupingBy(OrderItemDO::getOrderId));
    }
}
