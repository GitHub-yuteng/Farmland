package com.harvest.oms.service.order.handler.update;

import com.harvest.core.service.annotation.BizLog;
import com.harvest.core.log.RecordLog;
import com.harvest.core.service.utils.BizLogUtils;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import com.harvest.oms.repository.domain.order.base.OrderTag;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.handler.AbstractBizOrderHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/3/19 3:39 PM
 * @Description: 移除标记
 **/
@Component
public class OrderUpdateTagAddHandler extends AbstractBizOrderHandler implements OrderUpdateHandler {

    @Override
    protected String update() {
        return "添加订单标记";
    }

    @Override
    public boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum) {
        return OrderSubmitUpdateField.UpdateEnum.TAG_ADD.equals(updateEnum);
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
        List<OrderTag> orderTags = order.getOrderTags();
        if (CollectionUtils.isNotEmpty(orderTags)) {

        }
        orderEventPublisher.publish(companyId, order.getOrderId(), OrderEventEnum.TAG_MODIFY);
        this.log(companyId, field, order);
    }

    @Override
    public void log(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        OrderOperationLog operationLog = OrderOperationLog.build(order.getOrderId(), RecordLog.OperationType.MODIFY, this.update(), "添加订单标记");
        BizLogUtils.log(operationLog);
    }
}
