package com.harvest.rule.repository.domain.match.warehouse.section;

import com.harvest.core.rule.RuleSection;
import com.harvest.rule.repository.domain.match.warehouse.WarehouseRule;
import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 3:32 PM
 * @Description: TODO
 **/
@Data
public class AddressRuleSection implements RuleSection {

    private static final long serialVersionUID = -7645355929822214935L;

    /**
     * 规则名称
     *
     * @return
     */
    @Override
    public String name() {
        return WarehouseRule.RULE + "地址规则";
    }
}
