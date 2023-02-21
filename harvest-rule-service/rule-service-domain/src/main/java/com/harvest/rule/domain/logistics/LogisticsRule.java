package com.harvest.rule.domain.logistics;

import com.harvest.core.rule.IRule;

/**
 * @Author: Alodi
 * @Date: 2023/2/20 6:05 PM
 * @Description: TODO
 **/
public class LogisticsRule implements IRule {

    private static final long serialVersionUID = 6338996064157768730L;

    /**
     * 规则名称
     *
     * @return
     */
    @Override
    public String name() {
        return "物流规则";
    }

}
