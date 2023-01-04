package com.harvest.oms.client.handler.order.section;

import com.harvest.core.utils.QueryUtils;
import com.harvest.oms.client.handler.order.OrderSectionHandler;
import com.harvest.oms.client.order.OrderReadClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.OrderItemDO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/1 6:24 PM
 * @Description: TODO
 **/
@Order(1)
@Component
public class OrderItemSectionHandler implements OrderSectionHandler {

    private final static Integer ORDER_ITEM_PARTITION_SIZE = 200;

    @Autowired
    private OrderReadClient orderReadClient;

    @Override
    public void fill(Long companyId, OrderInfoDO order) {
        this.batchFill(companyId, Collections.singleton(order));
    }

    @Override
    public void batchFill(Long companyId, Collection<OrderInfoDO> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }
        List<Long> orderIds = orders.stream().map(OrderInfoDO::getOrderId).collect(Collectors.toList());
        Map<Long, List<OrderItemDO>> orderIdItemMap = this.partitionBatch(companyId, orderIds);
        if (MapUtils.isEmpty(orderIdItemMap)) {
            return;
        }
        orders.forEach(orderInfoDO -> {
            Long orderId = orderInfoDO.getOrderId();
            Collection<OrderItemDO> orderItems = orderIdItemMap.get(orderId);
            orderInfoDO.setOrderItems(orderItems);
        });
    }

    /**
     * 如果订单Ids过多 分区分批查询优化
     *
     * @param companyId
     * @param orderIds
     * @return
     */
    private Map<Long, List<OrderItemDO>> partitionBatch(Long companyId, List<Long> orderIds) {
        // extension 大字段 影响IO 在丰富查询时考虑查询效率则延迟查出，判断存在对应的 tagValue 单独取对应的 扩展信息进行处理
        return QueryUtils.partitionMapExecute(orderIds, ORDER_ITEM_PARTITION_SIZE, f -> orderReadClient.mapOrderItemByOrderIds(companyId, f));
    }
}
