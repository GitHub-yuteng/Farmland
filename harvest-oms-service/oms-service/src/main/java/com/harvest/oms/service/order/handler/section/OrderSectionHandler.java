package com.harvest.oms.service.order.handler.section;

import com.harvest.oms.domain.order.OrderInfoDO;

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


    /**
     * 订单部分信息填充排序
     */
    interface Order {

        /**
         * 订单商品信息
         */
        int ORDER_ITEM_GOODS = 1;
        /**
         * 订单仓库信息
         */
        int ORDER_WAREHOUSE = 2;
        /**
         * 订单物流信息
         */
        int ORDER_LOGISTICS = 3;
        /**
         * 订单申报信息
         */
        int ORDER_DECLARATION = 4;
        /**
         * 订单店铺信息
         */
        int ORDER_SHOP = 5;
        /**
         * 订单自定义标签
         */
        int ORDER_CUSTOM_TAG = 6;

    }

}
