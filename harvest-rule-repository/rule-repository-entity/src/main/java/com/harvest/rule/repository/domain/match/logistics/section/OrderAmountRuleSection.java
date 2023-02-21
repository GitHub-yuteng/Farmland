package com.harvest.rule.repository.domain.match.logistics.section;

import com.harvest.core.domain.range.number.BigDecimalRange;
import com.harvest.core.rule.RuleSection;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 4:39 PM
 * @Description: TODO
 **/
@Data
public class OrderAmountRuleSection implements RuleSection {

    private static final long serialVersionUID = 2689266644800635306L;

    private BigDecimalRange amountRange;

    /**
     * 规则名称
     *
     * @return
     */
    @Override
    public String name() {
        return LogisticsRule.RULE + "订单金额规则";
    }
}
