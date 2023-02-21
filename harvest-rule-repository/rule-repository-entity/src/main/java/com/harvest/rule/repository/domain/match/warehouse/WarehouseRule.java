package com.harvest.rule.repository.domain.match.warehouse;

import com.harvest.core.rule.RuleSection;

/**
 * @Author: Alodi
 * @Date: 2023/2/20 5:13 PM
 * @Description: TODO
 **/
public class WarehouseRule implements RuleSection {

    private static final long serialVersionUID = -8778725979807868897L;

    /**
     * 规则名称
     *
     * @return
     */
    @Override
    public String name() {
        return "仓库规则";
    }

}
