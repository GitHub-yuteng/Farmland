package com.harvest.oms.service.order.handler.update;

import com.harvest.core.service.annotation.BizLog;
import com.harvest.core.enums.oms.OrderStatusEnum;
import com.harvest.core.log.RecordLog;
import com.harvest.core.service.utils.BizLogUtils;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.handler.AbstractBizOrderHandler;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/3/19 3:39 PM
 * @Description: 关闭订单
 **/
@Component
public class OrderUpdateCloseHandler extends AbstractBizOrderHandler implements OrderUpdateHandler {

    @Override
    protected String update() {
        return "关闭订单";
    }

    @Override
    public boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum) {
        return OrderSubmitUpdateField.UpdateEnum.CLOSE.equals(updateEnum);
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
        orderWriteRepositoryClient.updateOrderStatus(companyId, order.getOrderId(), OrderStatusEnum.CLOSED);
        orderEventPublisher.publish(companyId, order.getOrderId(), OrderEventEnum.CLOSE);
        this.log(companyId, field, order);
    }

    @Override
    public void log(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        OrderOperationLog operationLog = OrderOperationLog.build(order.getOrderId(), RecordLog.OperationType.MODIFY, this.update(), "关闭订单");
        BizLogUtils.log(operationLog);
    }
}
