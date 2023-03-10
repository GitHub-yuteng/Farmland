package com.harvest.oms.service.order.processor;

import com.harvest.oms.domain.order.OrderInfoDO;

/**
 * @Author: Alodi
 * @Date: 2023/2/5 11:21 PM
 * @Description: 订单发货模版处理器, 业务流程具像化
 **/
public interface OrderDeliveryProcessor {

    /**
     * 订单发货前置校验-报错处理
     *
     * @param companyId
     * @param order
     * @return
     */
    void check(Long companyId, OrderInfoDO order);

    /**
     * 订单发货前置处理-处理参数等
     *
     * @param companyId
     * @param order
     * @return
     */
    void beforeDelivery(Long companyId, OrderInfoDO order);

    /**
     * 业务处理过程
     *
     * @param companyId
     */
    void processDelivery(Long companyId, OrderInfoDO order);

    /**
     * 订单发货后置处理
     *
     * @param companyId
     * @param order
     */
    void afterDelivery(Long companyId, OrderInfoDO order);

    /**
     * 执行业务流程
     *
     * @param companyId
     * @param order
     */
    default void execute(Long companyId, OrderInfoDO order) {
        this.check(companyId, order);
        this.beforeDelivery(companyId, order);
        this.processDelivery(companyId, order);
        this.afterDelivery(companyId, order);
    }

}
