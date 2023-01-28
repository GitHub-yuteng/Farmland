package com.harvest.oms.service.order.handler;

import com.harvest.oms.domain.order.OrderInfoDO;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 3:50 PM
 * @Description: 公司订单特性处理
 **/
public interface OrderCompanyFeatureHandler {

    /**
     * @param companyId
     * @param orders
     */
    void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders);

}
