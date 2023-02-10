package com.harvest.core.service.redis.prefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 5:09 PM
 * @Description: TODO
 **/
public interface KeyModePrefix {

    String OMS_PREFIX = "oms:";
    String BASIC_PREFIX = "basic:";


    interface OMS {

        String ORDER_AUDIT_FLOW     = "flow:";
        String ORDER_BACK_STAT_TASK = "back-stat:";

    }

    interface Basic {

        String WEBCONFIG = "webConfig:";
    }
}
