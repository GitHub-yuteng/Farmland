package com.harvest.oms.service.order.handler.update;

import com.harvest.core.service.annotation.BizLog;
import com.harvest.core.log.RecordLog;
import com.harvest.core.service.utils.BizLogUtils;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import com.harvest.oms.repository.domain.order.base.OrderTag;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.handler.AbstractBizOrderHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/3/19 3:39 PM
 * @Description: 移除标记
 **/
@Component
public class OrderUpdateTagRemoveAllHandler extends AbstractBizOrderHandler implements OrderUpdateHandler {

    @Override
    protected String update() {
        return "移除全部订单标记";
    }

    @Override
    public boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum) {
        return OrderSubmitUpdateField.UpdateEnum.TAG_REMOVE_ALL.equals(updateEnum);
    }

    @Override
    public boolean check(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        if (CollectionUtils.isEmpty(order.getOrderTags())) {
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
        orderWriteRepositoryClient.tagRemoveAll(companyId, order.getOrderId());
        orderEventPublisher.publish(companyId, order.getOrderId(), OrderEventEnum.TAG_MODIFY);
        this.log(companyId, field, order);
    }

    @Override
    public void log(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        if (CollectionUtils.isEmpty(order.getOrderTags())) {
            return;
        }
        OrderOperationLog operationLog = OrderOperationLog.build(order.getOrderId(), RecordLog.OperationType.MODIFY, this.update(),
                "移除全部订单标记: " + JsonUtils.object2Json(order.getOrderTags().stream().map(OrderTag::getTagValue).collect(Collectors.toList())));
        BizLogUtils.log(operationLog);
    }
}
