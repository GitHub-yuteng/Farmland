package com.harvest.oms.service.order.handler.section;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.wms.WarehouseKey;
import com.harvest.oms.repository.domain.order.base.OrderWarehouse;
import com.harvest.oms.service.cache.CacheLoader;
import com.harvest.oms.service.order.handler.OrderSectionHandler;
import com.harvest.wms.repository.domain.WarehouseDO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/1 6:21 PM
 * @Description: 订单仓库信息
 * {@link OrderSectionHandler.Order#C}
 **/
@Order(3)
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
                .map(OrderWarehouse::getWarehouseId).distinct()
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(warehouseIds)) {
            return;
        }

        List<WarehouseDO> warehouseList = warehouseIds.stream().map(warehouseId -> {
            WarehouseKey warehouseKey = new WarehouseKey(companyId, warehouseId);
            WarehouseDO warehouseDO = CacheLoader.COMPANY_WAREHOUSE_CACHE.getIfPresent(warehouseKey);
            if (Objects.isNull(warehouseDO)) {
                LOGGER.error("OrderWareHouseSectionHandler#batchFill#该公司仓库不存在, companyId:{} ,warehouseId:{}", companyId, warehouseId);
            }
            return warehouseDO;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        Map<Long, WarehouseDO> warehouseIdMap = warehouseList.stream().collect(Collectors.toMap(WarehouseDO::getWarehouseId, Function.identity()));

        orders.forEach(order -> {
            OrderWarehouse orderWarehouse = order.getOrderWarehouse();
            if (Objects.isNull(orderWarehouse)) {
                return;
            }
            Long warehouseId = orderWarehouse.getWarehouseId();
            order.setWarehouse(warehouseIdMap.get(warehouseId));
        });
    }

}
