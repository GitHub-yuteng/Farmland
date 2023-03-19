package com.harvest.oms.service.order.handler.update;

import com.harvest.core.annotation.BizLog;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.handler.AbstractBizOrderHandler;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/3/19 3:39 PM
 * @Description: TODO
 **/
@Component
public class OrderUpdateAbnormalHandler extends AbstractBizOrderHandler implements OrderUpdateHandler {

    @Override
    protected String update() {
        return "提交异常";
    }

    @Override
    public boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum) {
        return OrderSubmitUpdateField.UpdateEnum.ABNORMAL.equals(updateEnum);
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
        orderWriteRepositoryClient.abnormal(companyId, field.getAbnormal(), order.getOrderId());
        this.log(companyId, field, order);
    }

    @Override
    public void log(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {

    }
}
