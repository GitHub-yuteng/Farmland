package com.harvest.oms.service.order.handler.section;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.service.order.handler.OrderSectionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

/**
 * @Author: Alodi
 * @Date: 2023/1/1 6:21 PM
 * @Description: 订单仓库信息
 * {@link OrderSectionHandler.Order#C}
 **/
@Order(3)
@Component
public class OrderWareHouseSectionHandler implements OrderSectionHandler {

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

    }

}
