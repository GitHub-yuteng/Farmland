package com.harvest.oms.service.order.handler.update;

import com.harvest.core.annotation.BizLog;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.log.RecordLog;
import com.harvest.core.service.utils.BizLogUtils;
import com.harvest.oms.cache.CacheLoader;
import com.harvest.oms.client.order.OrderMatchClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.warehouse.WarehouseKey;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.repository.client.order.OrderWriteRepositoryClient;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import com.harvest.oms.repository.domain.order.base.OrderWarehouse;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.event.OrderEventPublisher;
import com.harvest.oms.service.order.handler.AbstractBizOrderHandler;
import com.harvest.rule.domain.warehouse.WarehouseRuleMatch;
import com.harvest.rule.repository.domain.match.warehouse.WarehouseRuleCondition;
import com.harvest.wms.domain.WarehouseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 10:54 AM
 * @Description: 订单更新匹配仓库
 **/
@Component
public class OrderUpdateWarehouseMatchHandler extends AbstractBizOrderHandler implements OrderUpdateHandler {

    @Autowired
    private OrderMatchClient orderMatchClient;

    @Autowired
    private OrderEventPublisher orderEventPublisher;

    @Override
    protected String update() {
        return "更新订单仓库[匹配]";
    }

    @Override
    public boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum) {
        return OrderSubmitUpdateField.UpdateEnum.MATCH_WAREHOUSE.equals(updateEnum);
    }

    @BizLog
    @Override
    public void handle(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        WarehouseRuleCondition condition = new WarehouseRuleCondition();
        WarehouseRuleMatch warehouseRuleMatch = orderMatchClient.matchWarehouse(companyId, condition);
        WarehouseDO warehouse = CacheLoader.COMPANY_WAREHOUSE_CACHE.get(WarehouseKey.build(companyId, warehouseRuleMatch.getWarehouseId()));
        if (Objects.isNull(warehouse)) {
            throw new StandardRuntimeException(ExceptionCodes.OMS_MODULE_ERROR, "仓库匹配异常！");
        }

        OrderWarehouse orderWarehouse = new OrderWarehouse();
        orderWarehouse.setWarehouseId(warehouse.getWarehouseId());
        orderWarehouse.setWarehouseOwner(warehouse.getWarehouseOwner());
        orderWriteRepositoryClient.updateWarehouse(companyId, order.getOrderId(), orderWarehouse);
        orderEventPublisher.publish(companyId, order.getOrderId(), OrderEventEnum.UPDATE_WAREHOUSE);

        field.setWarehouseId(warehouse.getWarehouseId());
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
        Long warehouseId = field.getWarehouseId();
        WarehouseDO warehouse = CacheLoader.COMPANY_WAREHOUSE_CACHE.get(WarehouseKey.build(companyId, warehouseId));

        OrderOperationLog operationLog = OrderOperationLog.init();
        operationLog.setBusinessId(order.getOrderId());
        operationLog.setOperationType(RecordLog.OperationType.MODIFY);
        operationLog.setPrefix(this.update());
        operationLog.setContent(
                Log.ORIGINAL + (order.getWarehouse() != null ? order.getWarehouse().getWarehouseId() + ":" + order.getWarehouse().getWarehouse() : "") +
                        LINE_FEED +
                        Log.CHANGE + warehouseId + ":" + Objects.requireNonNull(warehouse).getWarehouse()
        );
        BizLogUtils.log(operationLog);
    }

}
