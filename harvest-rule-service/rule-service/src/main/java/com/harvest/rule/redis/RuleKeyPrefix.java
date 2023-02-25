package com.harvest.rule.redis;

import com.harvest.core.service.redis.prefix.BaseKeyPrefix;
import com.harvest.core.service.redis.prefix.KeyModePrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 3:14 PM
 * @Description: TODO
 **/
public class RuleKeyPrefix extends BaseKeyPrefix {

    protected final static String RULE_PREFIX = KeyModePrefix.RULE_PREFIX;

    public RuleKeyPrefix(String keyPrefix) {
        super(keyPrefix);
    }

    public RuleKeyPrefix(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

    @Override
    public String getKeyPrefix() {
        return RULE_PREFIX + super.getKeyPrefix();
    }

    @Override
    public String getKeyLock() {
        return RULE_PREFIX + super.getKeyLock();
    }
}
