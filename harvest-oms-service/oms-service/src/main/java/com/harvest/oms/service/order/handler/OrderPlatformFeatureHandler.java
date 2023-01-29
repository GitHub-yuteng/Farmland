package com.harvest.oms.service.order.handler;

import com.harvest.core.enums.oms.OrderSourceEnum;
import com.harvest.oms.domain.order.OrderInfoDO;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 3:50 PM
 * @Description: 平台订单特性处理
 **/
public interface OrderPlatformFeatureHandler {

    /**
     * 匹配对应平台处理器
     *
     * @param companyId
     * @return
     */
    default Collection<OrderInfoDO> filter(Long companyId, OrderSourceEnum orderSourceEnum, Collection<OrderInfoDO> orders) {
        return orders.stream().filter(order -> orderSourceEnum.equals(order.getOrderSource())).collect(Collectors.toList());
    }

    /**
     * @param companyId
     * @param orders
     */
    void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders);

}
