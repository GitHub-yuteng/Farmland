package com.harvest.rule.service.logistics;

import com.harvest.core.utils.JsonUtils;
import com.harvest.rule.client.logistics.LogisticsRuleClient;
import com.harvest.rule.domain.logistics.LogisticsRuleMatch;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRuleCondition;
import com.harvest.rule.service.RuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 7:26 PM
 * @Description: 物流匹配器
 **/
@Component
public class LogisticsMatchProcessor extends RuleContext<LogisticsRule, LogisticsRuleCondition, LogisticsRuleMatch> {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogisticsMatchProcessor.class);

    @Autowired
    private LogisticsRuleClient logisticsRuleClient;

    @Override
    protected String type() {
        return LogisticsRule.RULE;
    }

    @Override
    public LogisticsRuleMatch andMatch(Long companyId, LogisticsRuleCondition condition) {
        LogisticsRuleMatch match = super.andMatch(companyId, condition);
        if (Objects.isNull(match)) {
            LOGGER.error("LogisticsMatchProcessor#物流匹配#无对应匹配规则#匹配为默认规则, companyId:{} 匹配条件：{}", companyId, JsonUtils.object2Json(condition));
            return this.getDefaultRule(companyId);
        }
        return match;
    }

    @Override
    protected Collection<LogisticsRule> getRules(Long companyId, LogisticsRuleCondition condition) {
        return logisticsRuleClient.listLogisticsRule(companyId);
    }

    @Override
    protected LogisticsRuleMatch getDefaultRule(Long companyId) {
        return logisticsRuleClient.getDefaultRule(companyId);
    }

    @Override
    protected boolean isIgnore(Long companyId, LogisticsRule rule, LogisticsRuleCondition condition) {
        return false;
    }

    @Override
    protected LogisticsRuleMatch ruleMatch(Long companyId, LogisticsRule rule) {
        LogisticsRuleMatch logisticsRuleMatch = new LogisticsRuleMatch();
        logisticsRuleMatch.setLogisticsId(rule.getLogisticsId());
        logisticsRuleMatch.setChannelId(rule.getChannelId());
        logisticsRuleMatch.setRuleId(rule.getId());
        logisticsRuleMatch.setCompanyId(companyId);
        return logisticsRuleMatch;
    }
}
