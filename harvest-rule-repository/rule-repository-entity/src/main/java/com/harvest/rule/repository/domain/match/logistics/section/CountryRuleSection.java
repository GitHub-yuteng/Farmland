package com.harvest.rule.repository.domain.match.logistics.section;

import com.harvest.core.rule.RuleSection;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 3:57 PM
 * @Description: TODO
 **/
@Data
public class CountryRuleSection implements RuleSection {

    private static final long serialVersionUID = -4533437483822313257L;

    /**
     * 优先级
     *
     * @return
     */
    @Override
    public int priority() {
        return 1;
    }

    /**
     * 规则名称
     *
     * @return
     */
    @Override
    public String name() {
        return LogisticsRule.RULE + "国家规则";
    }
}
