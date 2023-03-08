package com.harvest.core.service.redis.prefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 5:09 PM
 * @Description: TODO
 **/
public interface KeyModePrefix {

    String OMS_PREFIX = "oms:";

    String RULE_PREFIX = "rule:";

    String BASIC_PREFIX = "basic:";


    interface OMS {

        String ORDER_TRIGGER        = "trigger:";
        String ORDER_AUDIT_FLOW     = "flow:";
        String ORDER_BACK_STAT_TASK = "back-stat:";

    }

    interface Rule {

        String LOGISTICS_RULE       = "logistics-rule:";
        String LOGISTICS_RULE_MATCH = "logistics-match-rule:";
    }

    interface Basic {

        String WEBCONFIG = "webConfig:";
    }
}
