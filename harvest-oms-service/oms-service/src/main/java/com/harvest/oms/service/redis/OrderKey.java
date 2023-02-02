package com.harvest.oms.service.redis;

import com.harvest.core.service.redis.BaseKeyPrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 1:01 PM
 * @Description: TODO
 **/
public class OrderKey extends BaseKeyPrefix {

    public OrderKey(String keyPrefix) {
        super(keyPrefix);
    }

    public OrderKey(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

}
