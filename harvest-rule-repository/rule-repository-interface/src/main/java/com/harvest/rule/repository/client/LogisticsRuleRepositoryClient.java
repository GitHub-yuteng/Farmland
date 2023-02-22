package com.harvest.rule.repository.client;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.rule.repository.constants.HarvestRuleRepositoryApplications;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 9:35 PM
 * @Description: TODO
 **/
@HarvestClient(value = HarvestRuleRepositoryApplications.SERVICE_NAME, path = HarvestRuleRepositoryApplications.LogisticsPath.LOGISTICS_RULE)
public interface LogisticsRuleRepositoryClient extends GlobalMacroDefinition {

    @ApiOperation("物流匹配规则")
    @PostMapping("/listLogisticsRule")
    Collection<LogisticsRule> listLogisticsRule(@RequestParam(COMPANY_ID) Long companyId);

}
