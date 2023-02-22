package com.harvest.rule.client.warehouse;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.rule.client.constants.HarvestRuleApplications;
import com.harvest.rule.domain.warehouse.WarehouseRuleMatch;
import com.harvest.rule.repository.domain.match.warehouse.WarehouseRuleCondition;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 9:29 AM
 * @Description: TODO
 **/
@HarvestClient(name = HarvestRuleApplications.SERVICE_NAME, path = HarvestRuleApplications.LogisticsPath.LOGISTICS_RULE)
public interface WarehouseRuleClient extends GlobalMacroDefinition {

    @ApiOperation("订单匹配仓库")
    @PostMapping("/matchWarehouse")
    WarehouseRuleMatch matchWarehouse(@RequestParam(COMPANY_ID) Long companyId, @RequestBody WarehouseRuleCondition condition);

}
