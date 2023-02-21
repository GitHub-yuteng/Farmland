package com.harvest.oms.service.order.strategy;

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
     * @param orderId   订单Id
     */
    void execute(long companyId, Long orderId);
}
