package com.harvest.oms.redis.flow;

import com.harvest.core.service.redis.prefix.KeyModePrefix;
import com.harvest.oms.redis.OmsKeyPrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 2:31 PM
 * @Description: 订单审核
 **/
public class OrderAuditFlowKey extends OmsKeyPrefix {

    public OrderAuditFlowKey(String keyPrefix) {
        super(keyPrefix);
    }

    public OrderAuditFlowKey(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

    /**
     * 订单审核
     */
    public static final OrderAuditFlowKey ORDER_AUDIT_KEY = new OrderAuditFlowKey(
            KeyModePrefix.OMS.ORDER_AUDIT_FLOW, ONE_MINUTE
    );

}
