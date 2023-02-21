package com.harvest.rule.repository.domain.match.logistics.section;

import com.harvest.core.domain.range.number.BigDecimalRange;
import com.harvest.core.rule.RuleSection;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 4:34 PM
 * @Description: TODO
 **/
@Data
public class PackageRuleSection implements RuleSection {

    private static final long serialVersionUID = 784698785341275641L;

    @ApiModelProperty("重量范围")
    private BigDecimalRange weightRange;

    @ApiModelProperty("体积范围")
    private BigDecimalRange volumeRange;

    @ApiModelProperty("长宽高三遍和范围")
    private BigDecimalRange sizeRange;

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
        return LogisticsRule.RULE + "包裹信息规则";
    }
}
