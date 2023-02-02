package com.harvest.oms.service.redis;

import com.harvest.core.service.redis.BaseKeyPrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 3:14 PM
 * @Description: TODO
 **/
public class OmsKeyPrefix extends BaseKeyPrefix {

    public OmsKeyPrefix(String keyPrefix) {
        super(keyPrefix);
    }

    public OmsKeyPrefix(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }
}
