package com.harvest.oms.service.order.handler.section;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.service.order.handler.OrderSectionHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/6 12:30 AM
 * @Description: 订单自定义标签
 **/
@Order(OrderSectionHandler.Order.ORDER_SHOP)
@Component
public class OrderShopSectionhandler implements OrderSectionHandler {

    @Override
    public void fill(Long companyId, OrderInfoDO order) {
        this.batchFill(companyId, Collections.singleton(order));
    }

    @Override
    public void batchFill(Long companyId, Collection<OrderInfoDO> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }

        List<Long> shopIds = orders.parallelStream().map(OrderInfoDO::getShopId).filter(Objects::nonNull).distinct().collect(Collectors.toList());

    }
}
