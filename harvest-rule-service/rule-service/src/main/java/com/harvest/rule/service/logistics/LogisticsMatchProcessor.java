package com.harvest.rule.service.logistics;

import com.harvest.rule.domain.logistics.LogisticsRuleMatch;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRuleCondition;
import com.harvest.rule.service.RuleContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 7:26 PM
 * @Description: 物流匹配
 **/
@Component
public class LogisticsMatchProcessor extends RuleContext<LogisticsRule, LogisticsRuleCondition, LogisticsRuleMatch> {

    /**
     * 规则类型
     *
     * @return
     */
    @Override
    protected String type() {
        return LogisticsRule.RULE;
    }

    /**
     * 根据 C 匹配到符合的 RM
     *
     * @param condition
     * @return
     */
    @Override
    public LogisticsRuleMatch andMatch(LogisticsRuleCondition condition) {
        LogisticsRuleMatch match = super.andMatch(condition);
        if (Objects.isNull(match)) {
            // 查询默认匹配规则
        }
        return match;
    }

    /**
     * 获取该设置全部规则
     *
     * @param condition
     * @return
     */
    @Override
    protected Collection<LogisticsRule> getRules(LogisticsRuleCondition condition) {
        return null;
    }

    /**
     * 忽略特殊匹配规则
     *
     * @param rule
     * @param condition
     * @return
     */
    @Override
    protected boolean isIgnore(LogisticsRule rule, LogisticsRuleCondition condition) {
        return super.isIgnore(rule, condition);
    }

    /**
     * 转换规则为匹配结果
     *
     * @param rule
     * @return
     */
    @Override
    protected LogisticsRuleMatch ruleMatch(LogisticsRule rule) {
        return null;
    }
}
