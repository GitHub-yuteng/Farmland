package com.harvest.oms.service.order.strategy;

import com.harvest.oms.domain.order.OrderInfoDO;

/**
 * @Author: Alodi
 * @Date: 2023/1/8 12:56 PM
 * @Description: 订单
 **/
public interface OrderRuleStrategy {

    /**
     * 执行订单策略
     *
     * @param companyId 公司Id
     * @param order     订单
     */
    void execute(long companyId, OrderInfoDO order);
}
