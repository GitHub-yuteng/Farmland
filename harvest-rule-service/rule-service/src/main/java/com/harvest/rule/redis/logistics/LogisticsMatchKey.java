package com.harvest.rule.redis.logistics;

import com.harvest.rule.redis.RuleKeyPrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/24 1:27 PM
 * @Description: TODO
 **/
public class LogisticsMatchKey extends RuleKeyPrefix {

    public LogisticsMatchKey(String keyPrefix) {
        super(keyPrefix);
    }

    public LogisticsMatchKey(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }
}
