package com.harvest.oms.service.order.handler.update;

import com.harvest.core.annotation.BizLog;
import com.harvest.core.log.RecordLog;
import com.harvest.core.service.utils.BizLogUtils;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.handler.AbstractBizOrderHandler;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/3/19 3:39 PM
 * @Description: 提交异常
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
        orderWriteRepositoryClient.abnormal(companyId, true, order.getOrderId());
        this.log(companyId, field, order);
    }

    @Override
    public void log(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        OrderOperationLog operationLog = OrderOperationLog.build(order.getOrderId(), RecordLog.OperationType.MODIFY, this.update(), "提交异常");
        BizLogUtils.log(operationLog);
    }
}
