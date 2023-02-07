package com.harvest.basic.service.redis;

import com.harvest.core.service.redis.prefix.BaseKeyPrefix;
import com.harvest.core.service.redis.prefix.KeyModePrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/6 4:41 PM
 * @Description: TODO
 **/
public class BasicKeyPrefix extends BaseKeyPrefix {

    protected final static String BASIC_PREFIX = KeyModePrefix.BASIC_PREFIX;

    public BasicKeyPrefix(String keyPrefix) {
        super(keyPrefix);
    }

    public BasicKeyPrefix(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

    @Override
    public String getKeyPrefix() {
        return BASIC_PREFIX + super.getKeyPrefix();
    }

    @Override
    public String getKeyLock() {
        return BASIC_PREFIX + super.getKeyLock();
    }
}
