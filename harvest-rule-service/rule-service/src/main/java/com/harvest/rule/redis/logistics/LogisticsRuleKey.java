package com.harvest.rule.redis.logistics;

import com.harvest.core.service.redis.prefix.KeyModePrefix;
import com.harvest.rule.redis.RuleKeyPrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/24 1:27 PM
 * @Description: 物流规则缓存
 **/
public class LogisticsRuleKey extends RuleKeyPrefix {

    public LogisticsRuleKey(String keyPrefix) {
        super(keyPrefix);
    }

    public LogisticsRuleKey(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

    public static final LogisticsRuleKey LOGISTICS_RULE_KEY = new LogisticsRuleKey(
            KeyModePrefix.Rule.LOGISTICS_RULE, ONE_MINUTE
    );
}
