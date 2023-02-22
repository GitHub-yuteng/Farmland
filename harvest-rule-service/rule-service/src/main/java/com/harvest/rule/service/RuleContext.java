package com.harvest.rule.service;

import com.harvest.core.rule.RuleManager;
import com.harvest.core.rule.confine.Rule;
import com.harvest.core.rule.confine.RuleCondition;
import com.harvest.core.rule.confine.RuleMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/2/20 2:35 PM
 * @Description: TODO
 **/
public abstract class RuleContext<RULE extends Rule, C extends RuleCondition, RM extends RuleMatch> {

    @Autowired
    private RuleManager ruleManager;

    /**
     * 根据 C 匹配到符合的 RM
     *
     * @param condition
     * @return
     */
    public RM andMatch(Long companyId, C condition) {
        Collection<RULE> rules = this.getRules(companyId, condition);
        if (CollectionUtils.isEmpty(rules)) {
            return null;
        }
        RM ruleMatch = null;
        for (RULE rule : rules) {
            if (ruleManager.andMatch(rule, condition)) {
                if (this.isIgnore(companyId, rule, condition)) {
                    continue;
                }
                ruleMatch = this.ruleMatch(companyId, rule);
                break;
            }
        }
        return ruleMatch;
    }

    /**
     * 根据 C 匹配到符合的 RM
     *
     * @param condition
     * @return
     */
    public RM orMatch(Long companyId, C condition) {
        Collection<RULE> rules = this.getRules(companyId, condition);
        if (CollectionUtils.isEmpty(rules)) {
            return null;
        }
        RM result = null;
        for (RULE rule : rules) {
            if (ruleManager.orMatch(rule, condition)) {
                if (this.isIgnore(companyId, rule, condition)) {
                    continue;
                }
                result = this.ruleMatch(companyId, rule);
                break;
            }
        }
        return result;
    }

    /**
     * 规则类型
     *
     * @return
     */
    protected abstract String type();

    /**
     * 获取该设置全部规则
     *
     * @param condition
     * @return
     */
    protected abstract Collection<RULE> getRules(Long companyId, C condition);

    /**
     * 获取默认规则
     *
     * @param companyId
     * @return
     */
    protected abstract RM getDefaultRule(Long companyId);

    /**
     * 忽略特殊匹配规则
     *
     * @param rule
     * @param condition
     * @return
     */
    protected boolean isIgnore(Long companyId, RULE rule, C condition) {
        return false;
    }

    /**
     * 转换规则为匹配结果
     *
     * @param rule
     * @return
     */
    protected abstract RM ruleMatch(Long companyId, RULE rule);

}
