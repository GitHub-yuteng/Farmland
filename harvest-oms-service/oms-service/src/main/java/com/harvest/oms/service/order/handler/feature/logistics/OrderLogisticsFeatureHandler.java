package com.harvest.oms.service.order.handler.feature.logistics;

import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.oms.domain.order.OrderInfoDO;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/25 3:50 PM
 * @Description: 物流特性处理
 **/
public interface OrderLogisticsFeatureHandler {

    /**
     * 匹配对应物流处理器
     *
     * @param companyId
     * @return
     */
    default Collection<OrderInfoDO> filter(Long companyId, LogisticsEnum logisticsEnum, Collection<OrderInfoDO> orders) {
        return orders.stream().filter(order -> logisticsEnum.equals(order.getLogisticsEnum())).collect(Collectors.toList());
    }

    /**
     * @param companyId
     * @param orders
     */
    void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders);

}
