package com.harvest.rule.repository.constants;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 9:37 PM
 * @Description: TODO
 **/
public interface HarvestRuleRepositoryApplications {

    String SERVICE_NAME = "harvest-rule-repository";

    String RULE_PATH = "/rule/repository";

    interface LogisticsPath {

        String LOGISTICS_RULE = RULE_PATH + "/LogisticsRuleRepositoryClient";

    }
}
