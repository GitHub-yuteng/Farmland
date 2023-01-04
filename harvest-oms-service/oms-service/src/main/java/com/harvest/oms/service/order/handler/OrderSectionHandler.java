package com.harvest.oms.service.order.handler;

import com.harvest.oms.domain.order.OrderInfoDO;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/1 6:19 PM
 * @Description: 订单部分信息处理器
 **/
public interface OrderSectionHandler {

    /**
     * @param companyId
     * @param order
     */
    void fill(Long companyId, OrderInfoDO order);

    /**
     * @param companyId
     * @param orders
     */
    void batchFill(Long companyId, Collection<OrderInfoDO> orders);

}
