package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.rule.domain.logistics.LogisticsRuleMatch;
import com.harvest.rule.domain.warehouse.WarehouseRuleMatch;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRuleCondition;
import com.harvest.rule.repository.domain.match.warehouse.WarehouseRuleCondition;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2023/02/21 9:48 PM
 * @Description: 订单匹配服务
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_MATCH)
public interface OrderMatchClient extends GlobalMacroDefinition {

    @ApiOperation("订单仓库匹配")
    @PostMapping("/matchWarehouse")
    WarehouseRuleMatch matchWarehouse(@RequestParam(COMPANY_ID) Long companyId, @RequestBody WarehouseRuleCondition condition);

    @ApiOperation("订单物流匹配")
    @PostMapping("/matchLogistics")
    LogisticsRuleMatch matchLogistics(@RequestParam(COMPANY_ID) Long companyId, @RequestBody LogisticsRuleCondition condition);

}
