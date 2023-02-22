package com.harvest.rule.client.logistics;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.context.SpringHelper;
import com.harvest.rule.client.constants.HarvestRuleApplications;
import com.harvest.rule.domain.logistics.LogisticsRuleMatch;
import com.harvest.rule.repository.client.LogisticsRuleRepositoryClient;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRuleCondition;
import com.harvest.rule.service.logistics.LogisticsMatchProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 9:29 AM
 * @Description: TODO
 **/
@HarvestService(path = HarvestRuleApplications.LogisticsPath.LOGISTICS_RULE)
public class LogisticsRuleClientImpl implements LogisticsRuleClient {

    @Autowired
    private LogisticsRuleRepositoryClient logisticsRuleRepositoryClient;

    @Override
    public Collection<LogisticsRule> listLogisticsRule(Long companyId) {
        Collection<LogisticsRule> logisticsRules = logisticsRuleRepositoryClient.listLogisticsRule(companyId);
        return logisticsRules;
    }

    @Override
    public LogisticsRuleMatch matchLogistics(Long companyId, LogisticsRuleCondition condition) {
        return SpringHelper.getBean(LogisticsMatchProcessor.class).andMatch(companyId, condition);
    }

}
