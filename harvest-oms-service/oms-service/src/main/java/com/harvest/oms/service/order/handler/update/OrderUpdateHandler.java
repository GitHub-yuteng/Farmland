package com.harvest.oms.service.order.handler.update;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 10:46 AM
 * @Description: TODO
 **/
public interface OrderUpdateHandler {

    /**
     * 匹配更新处理器
     *
     * @param companyId
     * @param updateEnum
     * @return
     */
    boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum);

    /**
     * 处理
     *
     * @param companyId
     * @param field
     * @param order
     */
    void handle(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order);

    /**
     * 记录处理日志
     *
     * @param companyId
     * @param field
     * @param order
     */
    void log(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order);
}
