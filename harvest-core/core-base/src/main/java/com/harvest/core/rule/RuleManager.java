package com.harvest.core.rule;

import com.harvest.core.rule.confine.Rule;
import com.harvest.core.rule.confine.RuleCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/20 10:12 AM
 * @Description: 规则管理器
 **/
@Component
public class RuleManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleManager.class);

    @Autowired(required = false)
    private Set<RuleMatcher<? extends RuleSection, ?>> matchers;

    /**
     * 该完整规则的细分规则 全部符合条件才能匹配
     *
     * @param rule
     * @param condition
     * @param <RULE>
     * @param <C>
     * @return
     */
    public <RULE extends Rule, C extends RuleCondition> boolean andMatch(RULE rule, C condition) {
        return this.and(this.ruleSection(rule), condition);
    }

    /**
     * 该完整规则的细分规则 任意符合条件才能匹配
     *
     * @param rule
     * @param condition
     * @param <RULE>
     * @param <C>
     * @return
     */
    public <RULE extends Rule, C extends RuleCondition> boolean orMatch(RULE rule, C condition) {
        return this.or(this.ruleSection(rule), condition);
    }

    /**
     * 切分完整规则的细分规则项
     *
     * @param rule
     * @param <RULE>
     * @return
     */
    private <RULE extends Rule> Collection<RuleSection> ruleSection(RULE rule) {
        if (rule == null) {
            return null;
        }
        Collection<RuleSection> ruleDetails = new ArrayList<>();
        Field[] fields = rule.getClass().getDeclaredFields();
        for (Field field : fields) {
            Class<?> type = field.getType();
            if (!RuleSection.class.isAssignableFrom(type)) {
                continue;
            }
            try {
                field.setAccessible(true);
                Object content = field.get(rule);
                if (Objects.isNull(content)) {
                    continue;
                }
                ruleDetails.add((RuleSection) content);
            } catch (IllegalAccessException e) {
                LOGGER.error("获取规则内容发生异常", e);
            }
        }
        return ruleDetails;
    }

    /**
     * 获取匹配的规则
     *
     * @param rule
     * @param <C>
     * @param <R>
     * @return
     */
    private <R extends RuleSection, C extends RuleCondition> RuleMatcher<R, C> getMatcher(R rule) {
        if (rule == null) {
            throw new IllegalArgumentException("Rule cannot be empty!");
        }
        Class<?> clazz = rule.getClass();
        RuleMatcher<? extends RuleSection, ?> handler = matchers.stream().filter(h -> h.handle() == clazz).findFirst().orElse(null);
        if (handler == null) {
            throw new IllegalArgumentException("No rule processor configured!");
        }
        return (RuleMatcher<R, C>) handler;
    }

    /**
     * 针对所有条件全部满足才返回true
     *
     * @param ruleGroup
     * @param condition
     * @param <R>
     * @param <C>
     * @return
     */
    private <R extends RuleSection, C extends RuleCondition> boolean and(Collection<R> ruleGroup, C condition) {
        if (CollectionUtils.isEmpty(ruleGroup)) {
            return false;
        }
        for (R rule : ruleGroup.stream().sorted(Comparator.comparingInt(R::priority)).collect(Collectors.toList())) {
            RuleMatcher<R, C> matcher = this.getMatcher(rule);
            if (!matcher.match(rule, condition)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 针对所有条件任意一个满足就返回true
     *
     * @param ruleGroup
     * @param condition
     * @param <R>
     * @param <C>
     * @return
     */
    private <R extends RuleSection, C extends RuleCondition> boolean or(Collection<R> ruleGroup, C condition) {
        if (CollectionUtils.isEmpty(ruleGroup)) {
            return false;
        }
        for (R rule : ruleGroup.stream().sorted(Comparator.comparingInt(R::priority)).collect(Collectors.toList())) {
            RuleMatcher<R, C> matcher = this.getMatcher(rule);
            if (matcher.match(rule, condition)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 按照优先级返回第一条符合的规则
     *
     * @param ruleGroup
     * @param condition
     * @param <R>
     * @param <C>
     * @return
     */
    public <R extends RuleSection, C extends RuleCondition> R findFirst(Collection<R> ruleGroup, C condition) {
        if (CollectionUtils.isEmpty(ruleGroup)) {
            return null;
        }
        return ruleGroup.stream()
                .sorted(Comparator.comparingInt(RuleSection::priority))
                .filter(rule -> this.getMatcher(rule).match(rule, condition))
                .findFirst()
                .orElse(null);
    }
}
