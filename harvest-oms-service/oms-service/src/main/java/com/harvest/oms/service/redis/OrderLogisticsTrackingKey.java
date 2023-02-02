package com.harvest.oms.service.redis;

import lombok.Builder;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 2:31 PM
 * @Description: TODO
 **/
@Builder
public class OrderLogisticsTrackingKey extends OmsKeyPrefix {

    public OrderLogisticsTrackingKey(String keyPrefix) {
        super(keyPrefix);
    }

    public OrderLogisticsTrackingKey(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

}
