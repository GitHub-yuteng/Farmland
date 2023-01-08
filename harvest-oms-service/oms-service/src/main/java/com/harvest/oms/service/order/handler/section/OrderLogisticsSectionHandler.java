package com.harvest.oms.service.order.handler.section;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.service.order.handler.OrderSectionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/8 1:34 AM
 * @Description: 订单物流信息
 * {@link OrderSectionHandler.Order#D}
 **/
@Order(4)
@Component
public class OrderLogisticsSectionHandler implements OrderSectionHandler {

    /**
     * @param companyId
     * @param order
     */
    @Override
    public void fill(Long companyId, OrderInfoDO order) {

    }

    /**
     * @param companyId
     * @param orders
     */
    @Override
    public void batchFill(Long companyId, Collection<OrderInfoDO> orders) {

    }
}
