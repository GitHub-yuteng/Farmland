package com.harvest.rule.repository.domain.match.logistics.section;

import com.harvest.core.rule.RuleSection;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import lombok.Data;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 4:23 PM
 * @Description: TODO
 **/
@Data
public class WarehouseRuleSection implements RuleSection {

    private static final long serialVersionUID = -8251492988866083094L;

    private List<String> warehouseIds;

    /**
     * 优先级
     *
     * @return
     */
    @Override
    public int priority() {
        return RuleSection.super.priority();
    }

    /**
     * 规则名称
     *
     * @return
     */
    @Override
    public String name() {
        return LogisticsRule.RULE + "仓库规则";
    }
}
