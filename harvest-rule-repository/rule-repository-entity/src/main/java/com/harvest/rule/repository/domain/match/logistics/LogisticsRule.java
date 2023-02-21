package com.harvest.rule.repository.domain.match.logistics;


import com.harvest.core.rule.confine.Rule;
import com.harvest.rule.repository.domain.match.logistics.section.CountryRuleSection;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/20 6:05 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class LogisticsRule extends Rule {

    private static final long serialVersionUID = 6338996064157768730L;

    public static final String RULE = "[物流匹配]";

    private CountryRuleSection countryRule;

}
