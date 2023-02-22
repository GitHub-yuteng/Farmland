package com.harvest.rule.client.warehouse;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.rule.client.constants.HarvestRuleApplications;
import com.harvest.rule.domain.warehouse.WarehouseRuleMatch;
import com.harvest.rule.repository.domain.match.warehouse.WarehouseRuleCondition;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 9:29 AM
 * @Description: TODO
 **/
@HarvestService(path = HarvestRuleApplications.WarehousePath.WAREHOUSE_RULE)
public class WarehouseRuleClientImpl implements WarehouseRuleClient {

    @Override
    public WarehouseRuleMatch matchWarehouse(Long companyId, WarehouseRuleCondition condition) {
        return null;
    }
}
