package com.harvest.oms.service.order.handler.feature.company;

import com.harvest.oms.domain.order.OrderInfoDO;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 3:50 PM
 * @Description: 公司订单特性处理
 **/
public interface OrderCompanyFeatureHandler {

    /**
     * 匹配对应公司处理器
     *
     * @param companyId
     * @return
     */
    default boolean match(Long companyId) {
        return false;
    }

    /**
     * 批量处理特殊公司订单特性
     *
     * @param companyId
     * @param orders
     */
    void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders);

}
