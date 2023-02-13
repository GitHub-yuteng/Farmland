package com.harvest.oms.redis.trigger;

import com.harvest.core.service.redis.prefix.KeyModePrefix;
import com.harvest.oms.redis.OmsKeyPrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 2:31 PM
 * @Description: 订单触发器
 **/
public class OrderTriggerKey extends OmsKeyPrefix {

    public OrderTriggerKey(String keyPrefix) {
        super(keyPrefix);
    }

    public OrderTriggerKey(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

    /**
     * 查询触发器
     */
    public final static OrderTriggerKey ORDER_QUERY_TRIGGER_KEY = new OrderTriggerKey(
            KeyModePrefix.OMS.ORDER_TRIGGER + "interval-limit-query:", ONE_MINUTE
    );

}
