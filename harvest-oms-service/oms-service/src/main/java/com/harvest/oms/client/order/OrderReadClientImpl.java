package com.harvest.oms.client.order;

import com.google.common.collect.Maps;
import com.harvest.core.context.SpringHelper;
import com.harvest.core.feign.annotation.HarvestService;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.client.order.OrderReadRepositoryClient;
import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 10:24 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_READ)
public class OrderReadClientImpl implements OrderReadClient {

    @Autowired
    private OrderReadRepositoryClient orderReadRepositoryClient;

    @Override
    public OrderInfoDO get(Long companyId, Long orderId) {
        return SpringHelper.getBean(OrderRichQueryClient.class).getOrderRich(companyId, orderId);
    }

    @Override
    public Collection<OrderInfoDO> listOrderByOrderIds(Long companyId, List<Long> orderIds) {
        PageOrderConditionQuery condition = new PageOrderConditionQuery();
        condition.setOrderIds(orderIds);
        return SpringHelper.getBean(OrderRichQueryClient.class).listQueryOrderRich(companyId, condition);
    }

    @Override
    public Collection<OrderItemSimplePO> listOrderItemByOrderId(Long companyId, Long orderId) {
        Map<Long, List<OrderItemSimplePO>> map = this.mapOrderItemByOrderIds(companyId, Collections.singletonList(orderId));
        if (MapUtils.isEmpty(map)) {
            return Collections.emptyList();
        }
        return map.get(orderId);
    }

    @Override
    public Map<Long, List<OrderItemSimplePO>> mapOrderItemByOrderIds(Long companyId, List<Long> orderIds) {
        Collection<OrderItemSimplePO> orderItemSimplePOList = orderReadRepositoryClient.listOrderItemByOrderIds(companyId, orderIds);
        if (CollectionUtils.isEmpty(orderItemSimplePOList)) {
            return Maps.newHashMap();
        }
        return orderItemSimplePOList.stream().collect(Collectors.groupingBy(OrderItemSimplePO::getOrderId));
    }
}
