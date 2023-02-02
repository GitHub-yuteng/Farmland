package com.harvest.core.service.redis;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 5:09 PM
 * @Description: TODO
 **/
public interface KeyModePrefix {

    interface OMS {

        String OMS_PREFIX = "oms:";

        String ORDER_BACK_STAT_TASK = OMS_PREFIX + "back-stat:";

    }
}
