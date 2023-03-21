package com.harvest.oms.service.order.handler.update;

import com.harvest.core.service.annotation.BizLog;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.handler.AbstractBizOrderUpdateHandler;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 10:54 AM
 * @Description: TODO
 **/
@Component
public class OrderUpdateLogisticsHandler extends AbstractBizOrderUpdateHandler implements OrderUpdateHandler {

    @Override
    protected String update() {
        return "更新订单物流";
    }

    @Override
    public boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum) {
        return OrderSubmitUpdateField.UpdateEnum.LOGISTICS.equals(updateEnum);
    }

    @Override
    public boolean check(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        return OrderUpdateHandler.super.check(companyId, field, order);
    }

    @BizLog
    @Override
    public void handle(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        if (!this.check(companyId, field, order)) {
            return;
        }
        this.log(companyId, field, order);
    }

    /**
     * 记录处理日志
     *
     * @param companyId
     * @param field
     * @param order
     */
    @Override
    public void log(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {

    }
}
