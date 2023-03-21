package com.harvest.oms.service.order.handler;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.utils.BeanUtils;
import com.harvest.oms.client.order.OrderReadClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.client.order.OrderWriteRepositoryClient;
import com.harvest.oms.service.order.event.OrderEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 11:04 AM
 * @Description: TODO
 **/
public abstract class AbstractOrderHandler implements GlobalMacroDefinition {

    @Autowired
    protected OrderWriteRepositoryClient orderWriteRepositoryClient;

    @Autowired
    protected OrderEventPublisher orderEventPublisher;

    @Autowired
    protected OrderReadClient orderReadClient;

    /**
     * 获取订单信息
     *
     * @param companyId
     * @param orderId
     * @return
     */
    protected OrderInfoDO getOrderRich(Long companyId, Long orderId) {
        return orderReadClient.getOrderRich(companyId, orderId);
    }

    /**
     * 获取订单信息
     *
     * @param companyId
     * @param orderIds
     * @return
     */
    protected Collection<OrderInfoDO> listOrderRich(Long companyId, List<Long> orderIds) {
        return orderReadClient.listOrderRich(companyId, orderIds);
    }

    /**
     * 过滤关闭的订单明细
     *
     * @param order
     * @return
     */
    protected OrderInfoDO filterOrderItems(OrderInfoDO order) {
        OrderInfoDO clone = BeanUtils.clone(order);
        clone.getOrderItems().removeIf(item -> item.getOrderTags().contains(1));
        return clone;
    }


}
