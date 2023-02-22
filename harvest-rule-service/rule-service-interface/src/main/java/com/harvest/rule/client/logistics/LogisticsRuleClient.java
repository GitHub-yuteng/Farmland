package com.harvest.rule.client.logistics;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.rule.client.constants.HarvestRuleApplications;
import com.harvest.rule.domain.logistics.LogisticsRuleMatch;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRuleCondition;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 9:29 AM
 * @Description: TODO
 **/
@HarvestClient(name = HarvestRuleApplications.SERVICE_NAME, path = HarvestRuleApplications.LogisticsPath.LOGISTICS_RULE)
public interface LogisticsRuleClient extends GlobalMacroDefinition {

    @ApiOperation("物流匹配规则")
    @PostMapping("/listLogisticsRule")
    Collection<LogisticsRule> listLogisticsRule(@RequestParam(COMPANY_ID) Long companyId);

    @ApiOperation("订单匹配物流")
    @PostMapping("/matchLogistics")
    LogisticsRuleMatch matchLogistics(@RequestParam(COMPANY_ID) Long companyId, @RequestBody LogisticsRuleCondition condition);

}
