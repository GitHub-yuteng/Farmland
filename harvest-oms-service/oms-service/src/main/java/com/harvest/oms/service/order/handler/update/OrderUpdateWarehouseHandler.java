package com.harvest.oms.service.order.handler.update;

import com.harvest.core.annotation.BizLog;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.log.AbstractOperationLog;
import com.harvest.core.service.utils.BizLogUtils;
import com.harvest.oms.cache.CacheLoader;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.warehouse.WarehouseKey;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.repository.client.order.OrderWriteRepositoryClient;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import com.harvest.oms.repository.domain.order.base.OrderWarehouse;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.event.OrderEventPublisher;
import com.harvest.oms.service.order.handler.AbstractBizOrderHandler;
import com.harvest.wms.domain.WarehouseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 10:54 AM
 * @Description: 订单更新仓库
 **/
@Component
public class OrderUpdateWarehouseHandler extends AbstractBizOrderHandler implements OrderUpdateHandler {

    @Autowired
    private OrderWriteRepositoryClient orderWriteRepositoryClient;

    @Autowired
    private OrderEventPublisher orderEventPublisher;

    @Override
    public boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum) {
        return OrderSubmitUpdateField.UpdateEnum.WAREHOUSE.equals(updateEnum);
    }

    /**
     * 检查
     *
     * @param companyId
     * @param field
     * @param order
     */
    @Override
    public boolean check(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        Long warehouseId = field.getWarehouseId();
        if (Objects.isNull(warehouseId)) {
            throw new StandardRuntimeException(ExceptionCodes.OMS_MODULE_ERROR, "请检查仓库Id！");
        }
        WarehouseDO warehouse = CacheLoader.COMPANY_WAREHOUSE_CACHE.get(WarehouseKey.build(companyId, warehouseId));
        if (Objects.isNull(warehouse)) {
            throw new StandardRuntimeException(ExceptionCodes.OMS_MODULE_ERROR, "该公司不存在该仓库！");
        }
        if (Objects.nonNull(order.getWarehouse()) && order.getWarehouse().getWarehouseId().equals(warehouseId)) {
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
        Long warehouseId = field.getWarehouseId();
        WarehouseDO warehouse = CacheLoader.COMPANY_WAREHOUSE_CACHE.get(WarehouseKey.build(companyId, warehouseId));
        OrderWarehouse orderWarehouse = new OrderWarehouse();
        orderWarehouse.setWarehouseId(warehouseId);
        orderWarehouse.setWarehouseOwner(Objects.requireNonNull(warehouse).getWarehouseOwner());
        orderWriteRepositoryClient.updateWarehouse(companyId, order.getOrderId(), orderWarehouse);
        orderEventPublisher.publish(companyId, order.getOrderId(), OrderEventEnum.UPDATE_WAREHOUSE);
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
        operationLog.setOrderNo(order.getOrderNo());
        operationLog.setOperationType(AbstractOperationLog.OperationType.MODIFY);
        operationLog.setPrefix(this.update());
        operationLog.setContent(
                Log.ORIGINAL + (order.getWarehouse() != null ? order.getWarehouse().getWarehouseId() + ":" + order.getWarehouse().getWarehouse() : "") +
                LINE_FEED +
                Log.CHANGE + warehouseId + ":" + Objects.requireNonNull(warehouse).getWarehouse()
        );
        BizLogUtils.log(operationLog);
    }

    @Override
    protected String update() {
        return "更新订单仓库";
    }
}
