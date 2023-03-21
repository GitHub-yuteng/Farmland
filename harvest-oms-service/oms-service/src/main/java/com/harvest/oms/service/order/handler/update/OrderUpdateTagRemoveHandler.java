package com.harvest.oms.service.order.handler.update;

import com.harvest.core.log.RecordLog;
import com.harvest.core.service.annotation.BizLog;
import com.harvest.core.service.utils.BizLogUtils;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import com.harvest.oms.repository.domain.order.base.OrderTag;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.handler.AbstractOrderUpdateHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/3/19 3:39 PM
 * @Description: 移除标记
 **/
@Component
public class OrderUpdateTagRemoveHandler extends AbstractOrderUpdateHandler implements OrderUpdateHandler {

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
        if (CollectionUtils.isEmpty(field.getTagValues()) || CollectionUtils.isEmpty(order.getOrderTags())) {
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
        List<Integer> tagValues = field.getTagValues();
        List<Integer> orderTags = order.getOrderTags().stream().map(OrderTag::getTagValue).collect(Collectors.toList());
        tagValues.removeIf(tag -> !orderTags.contains(tag));

        // 没有涵盖移除此订单标记
        if (CollectionUtils.isEmpty(tagValues)) {
            return;
        }
        orderWriteRepositoryClient.tagRemove(companyId, order.getOrderId(), tagValues);
        orderEventPublisher.publish(companyId, order.getOrderId(), OrderEventEnum.TAG_MODIFY);
        field.setTagValues(tagValues);
        this.log(companyId, field, order);
    }

    @Override
    public void log(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        OrderOperationLog operationLog = OrderOperationLog.build(order.getOrderId(), RecordLog.OperationType.MODIFY, this.update(),
                "移除标记: " + JsonUtils.object2Json(field.getTagValues()));
        BizLogUtils.log(operationLog);

    }
}
