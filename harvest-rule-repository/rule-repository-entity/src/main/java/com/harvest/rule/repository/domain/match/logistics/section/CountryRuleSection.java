package com.harvest.rule.repository.domain.match.logistics.section;

import com.harvest.core.rule.RuleSection;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import lombok.Data;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 3:57 PM
 * @Description: TODO
 **/
@Data
public class CountryRuleSection implements RuleSection {

    private static final long serialVersionUID = -4533437483822313257L;

    private List<String> countryCodes;

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public String name() {
        return LogisticsRule.RULE + "国家规则";
    }


}
