package com.harvest.oms.service.order.handler;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.service.order.handler.section.OrderItemGoodsSectionHandler;
import com.harvest.oms.service.order.handler.section.OrderLogisticsSectionHandler;
import com.harvest.oms.service.order.handler.section.OrderWareHouseSectionHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/1 6:19 PM
 * @Description: 订单领域模型部分信息填充处理器
 **/
public interface OrderSectionHandler {

    /**
     * @param companyId
     * @param order
     */
    void fill(Long companyId, OrderInfoDO order);

    /**
     * @param companyId
     * @param orders
     */
    void batchFill(Long companyId, Collection<OrderInfoDO> orders);


    @Getter
    @AllArgsConstructor
    enum Order {

        /**
         * 订单部分信息填充排序
         */
        A(1, OrderItemGoodsSectionHandler.class),
        B(2, OrderWareHouseSectionHandler.class),
        C(3, OrderLogisticsSectionHandler.class),

        ;

        public final int order;
        public final Class<?> clazz;

    }

}
