package com.harvest.oms.redis.flow;

import com.harvest.core.service.redis.prefix.KeyModePrefix;
import com.harvest.oms.redis.OmsKeyPrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 2:31 PM
 * @Description: 订单发货
 **/
public class OrderDeliveryFlowKey extends OmsKeyPrefix {

    public OrderDeliveryFlowKey(String keyPrefix) {
        super(keyPrefix);
    }

    public OrderDeliveryFlowKey(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

    /**
     * 订单发货
     */
    public static final OrderDeliveryFlowKey ORDER_DELIVERY_FLOW_KEY = new OrderDeliveryFlowKey(
            KeyModePrefix.OMS.ORDER_DELIVERY_FLOW, ONE_MINUTE
    );

}
