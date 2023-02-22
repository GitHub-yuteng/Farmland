package com.harvest.core.path;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:16 PM
 * @Description: 订单路径定义
 **/
public interface HarvestRulePath {

    String RULE_PATH = "/harvest/rule";

    interface LogisticsRule {

        String LOGISTICS_RULE = RULE_PATH + "/logistics";

    }
}
