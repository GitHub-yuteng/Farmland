package com.harvest.oms.service.redis;

import com.harvest.core.service.redis.prefix.BaseKeyPrefix;
import com.harvest.core.service.redis.prefix.KeyModePrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 3:14 PM
 * @Description: TODO
 **/
public class OmsKeyPrefix extends BaseKeyPrefix {

    protected final static String OMS_PREFIX = KeyModePrefix.OMS_PREFIX;

    public OmsKeyPrefix(String keyPrefix) {
        super(keyPrefix);
    }

    public OmsKeyPrefix(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

    @Override
    public String getKeyPrefix() {
        return OMS_PREFIX + super.getKeyPrefix();
    }
}
