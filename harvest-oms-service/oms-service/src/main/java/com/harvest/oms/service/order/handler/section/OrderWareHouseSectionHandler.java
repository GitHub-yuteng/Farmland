package com.harvest.oms.service.order.handler.section;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.domain.order.base.OrderWarehouse;
import com.harvest.oms.cache.CacheLoader;
import com.harvest.wms.repository.service.domain.warehouse.simple.WarehouseSimplePO;
import com.harvest.wms.repository.service.repository.domain.WarehouseDO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/1 6:21 PM
 * @Description: 订单仓库信息
 **/
@Order(OrderSectionHandler.Order.ORDER_WAREHOUSE)
@Component
public class OrderWareHouseSectionHandler implements OrderSectionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderWareHouseSectionHandler.class);

    /**
     * @param companyId
     * @param order
     */
    @Override
    public void fill(Long companyId, OrderInfoDO order) {
        this.batchFill(companyId, Collections.singleton(order));
    }

    /**
     * @param companyId
     * @param orders
     */
    @Override
    public void batchFill(Long companyId, Collection<OrderInfoDO> orders) {
        List<Long> warehouseIds = orders.stream()
                .map(OrderInfoDO::getOrderWarehouse)
                .filter(Objects::nonNull)
                .map(OrderWarehouse::getWarehouseId)
                .distinct()
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(warehouseIds)) {
            return;
        }

        Collection<WarehouseSimplePO> warehouseList = CacheLoader.COMPANY_ALL_WAREHOUSE_CACHE.get(companyId);
        if (CollectionUtils.isEmpty(warehouseList)) {
            LOGGER.error("OrderWareHouseSectionHandler#batchFill#该公司仓库为空, 请检查数据！, companyId:{}", companyId);
            orders.forEach(order -> {
                OrderWarehouse orderWarehouse = order.getOrderWarehouse();
                if (Objects.isNull(orderWarehouse)) {
                    return;
                }
                WarehouseDO warehouseDO = new WarehouseDO();
                warehouseDO.setWarehouse("[仓库数据异常]");
                order.setWarehouse(warehouseDO);
            });
            return;
        }

        Map<Long, WarehouseSimplePO> warehouseIdMap = warehouseList.stream().collect(Collectors.toMap(WarehouseSimplePO::getWarehouseId, Function.identity()));
        orders.forEach(order -> {
            OrderWarehouse orderWarehouse = order.getOrderWarehouse();
            if (Objects.isNull(orderWarehouse)) {
                return;
            }
            Long warehouseId = orderWarehouse.getWarehouseId();
            WarehouseSimplePO warehouseSimplePO = warehouseIdMap.get(warehouseId);
            if (Objects.isNull(warehouseSimplePO)) {
                LOGGER.error("OrderWareHouseSectionHandler#batchFill#该公司仓库不存在, 请检查数据！, companyId:{}, warehouseId:{}", companyId, warehouseId);
                return;
            }

            WarehouseDO warehouseDO = new WarehouseDO();
            BeanUtils.copyProperties(warehouseSimplePO, warehouseDO);
            order.setWarehouse(warehouseDO);
        });
    }

}
