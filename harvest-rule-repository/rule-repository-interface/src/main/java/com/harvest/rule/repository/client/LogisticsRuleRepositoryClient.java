package com.harvest.rule.repository.client;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.rule.repository.constants.HarvestRuleRepositoryApplications;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 9:35 PM
 * @Description: TODO
 **/
@HarvestClient(value = HarvestRuleRepositoryApplications.SERVICE_NAME, path = HarvestRuleRepositoryApplications.LogisticsPath.LOGISTICS_RULE)
public interface LogisticsRuleRepositoryClient {

    @PostMapping("/listLogisticsRule")
    Collection<LogisticsRule> listLogisticsRule(Long companyId);

}
