package com.harvest.rule.service.logistics.match;

import com.harvest.core.rule.RuleMatcher;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRuleCondition;
import com.harvest.rule.repository.domain.match.logistics.section.LogisticsCountryRuleSection;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 12:43 AM
 * @Description: TODO
 **/
@Component
public class CountryRuleMatcher implements RuleMatcher<LogisticsCountryRuleSection, LogisticsRuleCondition> {

    @Override
    public boolean match(LogisticsCountryRuleSection ruleSection, LogisticsRuleCondition condition) {
        if (StringUtils.isEmpty(condition.getCountryCode()) || CollectionUtils.isEmpty(ruleSection.getCountryCodes())) {
            return Boolean.FALSE;
        }
        Set<String> countryCodes = ruleSection.getCountryCodes().stream()
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(countryCodes)) {
            return Boolean.FALSE;
        }
        return countryCodes.contains(condition.getCountryCode());
    }
}
