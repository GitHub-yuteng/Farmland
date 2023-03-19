package com.harvest.oms.service.order.handler.update;

import com.harvest.core.annotation.BizLog;
import com.harvest.core.log.RecordLog;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.handler.AbstractBizOrderHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/3/19 3:39 PM
 * @Description: 移除标记
 **/
@Component
public class OrderUpdateTagRemoveHandler extends AbstractBizOrderHandler implements OrderUpdateHandler {

    @Override
    protected String update() {
        return "移除订单标记";
    }

    @Override
    public boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum) {
        return OrderSubmitUpdateField.UpdateEnum.TAG_REMOVE.equals(updateEnum);
    }

    @Override
    public boolean check(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        if (CollectionUtils.isEmpty(field.getTagValues())) {
            return false;
        }
        return true;
    }

    @BizLog
    @Override
    public void handle(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        if (!this.check(companyId, field, order)) {
            return;
        }
        orderWriteRepositoryClient.tagRemove(companyId, order.getOrderId(), field.getTagValues());
        orderEventPublisher.publish(companyId, order.getOrderId(), OrderEventEnum.TAG_MODIFY);
        this.log(companyId, field, order);
    }

    @Override
    public void log(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        OrderOperationLog.build(order.getOrderId(), RecordLog.OperationType.MODIFY, "移除标记", "移除标记");
    }
}
