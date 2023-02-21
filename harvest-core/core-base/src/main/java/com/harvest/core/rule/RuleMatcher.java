package com.harvest.core.rule;

import com.harvest.core.rule.confine.RuleCondition;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: Alodi
 * @Date: 2023/2/20 10:08 AM
 * @Description: TODO
 **/
public interface RuleMatcher<R extends RuleSection, C extends RuleCondition> {

    /**
     * 匹配规则条件, 是否满足条件
     *
     * @param rule
     * @param condition
     * @return
     */
    boolean match(R rule, C condition);

    /**
     * 处理规则类
     *
     * @return 处理的规则类
     */
    default Class<R> handle() {
        Type[] types = ((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getActualTypeArguments();
        return (Class<R>) types[0];
    }

}
