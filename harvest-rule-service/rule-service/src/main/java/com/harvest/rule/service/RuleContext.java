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
    public RM andMatch(C condition) {
        Collection<RULE> rules = this.getRules(condition);
        if (CollectionUtils.isEmpty(rules)) {
            return null;
        }
        RM ruleMatch = null;
        for (RULE rule : rules) {
            if (ruleManager.andMatch(rule, condition)) {
                if (this.isIgnore(rule, condition)) {
                    continue;
                }
                ruleMatch = this.ruleMatch(rule);
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
    public RM orMatch(C condition) {
        Collection<RULE> rules = this.getRules(condition);
        if (CollectionUtils.isEmpty(rules)) {
            return null;
        }
        RM result = null;
        for (RULE rule : rules) {
            if (ruleManager.orMatch(rule, condition)) {
                if (this.isIgnore(rule, condition)) {
                    continue;
                }
                result = this.ruleMatch(rule);
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
    protected abstract Collection<RULE> getRules(C condition);

    /**
     * 忽略特殊匹配规则
     *
     * @param rule
     * @param condition
     * @return
     */
    protected boolean isIgnore(RULE rule, C condition) {
        return false;
    }

    /**
     * 转换规则为匹配结果
     *
     * @param rule
     * @return
     */
    protected abstract RM ruleMatch(RULE rule);

}
