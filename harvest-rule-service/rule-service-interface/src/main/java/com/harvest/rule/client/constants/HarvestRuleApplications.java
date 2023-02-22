package com.harvest.rule.client.constants;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 9:30 AM
 * @Description: TODO
 **/
public interface HarvestRuleApplications {

    String SERVICE_NAME = "harvest-rule-service";

    String RULE_PATH = "/rule";

    interface LogisticsPath {

        String LOGISTICS_RULE = RULE_PATH + "/LogisticsRuleClient";

    }

    interface WarehousePath {

        String WAREHOUSE_RULE = RULE_PATH + "/WarehouseRuleClient";

    }
}
