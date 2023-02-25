package com.harvest.oms.service.order.handler.section;

import com.harvest.core.context.SpringHelper;
import com.harvest.oms.client.order.OrderReadClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/6 12:30 AM
 * @Description: 订单申报信息
 **/
@Order(OrderSectionHandler.Order.ORDER_DECLARATION)
@Component
public class OrderDeclarationSectionhandler implements OrderSectionHandler {

    @Override
    public void fill(Long companyId, OrderInfoDO order) {
        this.batchFill(companyId, Collections.singleton(order));
    }

    @Override
    public void batchFill(Long companyId, Collection<OrderInfoDO> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }

        List<Long> orderIds = orders.parallelStream().map(OrderInfoDO::getOrderId).distinct().collect(Collectors.toList());
        OrderReadClient bean = SpringHelper.getBean(OrderReadClient.class);
    }
}
