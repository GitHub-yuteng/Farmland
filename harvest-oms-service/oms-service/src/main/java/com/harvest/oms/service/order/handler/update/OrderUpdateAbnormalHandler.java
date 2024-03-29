package com.harvest.oms.service.order.handler.update;

import com.harvest.core.log.RecordLog;
import com.harvest.core.service.annotation.BizLog;
import com.harvest.core.service.utils.BizLogUtils;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.repository.enums.OperationPrefixEnum;
import com.harvest.oms.service.order.handler.AbstractOrderUpdateHandler;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/3/19 3:39 PM
 * @Description: 提交异常
 **/
@Component
public class OrderUpdateAbnormalHandler extends AbstractOrderUpdateHandler implements OrderUpdateHandler {

    @Override
    protected String update() {
        return OperationPrefixEnum.ABNORMAL.getPrefix();
    }

    @Override
    public boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum) {
        return OrderSubmitUpdateField.UpdateEnum.ABNORMAL.equals(updateEnum);
    }

    @Override
    public boolean check(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        return true;
    }

    @BizLog
    @Override
    public void handle(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        if (!this.check(companyId, field, order)) {
            return;
        }
        orderWriteRepositoryClient.abnormal(companyId, field.getAbnormal(), order.getOrderId());
        orderEventPublisher.publish(companyId, order.getOrderId(), OrderEventEnum.ABNORMAL);
        this.log(companyId, field, order);
    }

    @Override
    public void log(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        OrderOperationLog operationLog = OrderOperationLog.build(order.getOrderId(), RecordLog.OperationType.MODIFY, this.update(), field.getAbnormal() ? "提交异常" : "恢复正常");
        BizLogUtils.log(operationLog);
    }
}
