package com.harvest.rule.repository.domain.match.logistics.section;

import com.harvest.core.domain.range.number.BigDecimalRange;
import com.harvest.core.rule.RuleSection;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 4:42 PM
 * @Description: TODO
 **/
@Data
public class GoodsSizeRuleSection implements RuleSection {

    private static final long serialVersionUID = 4495759311956107209L;

    @ApiModelProperty("长度范围")
    private BigDecimalRange lengthRange;

    @ApiModelProperty("宽度范围")
    private BigDecimalRange widthRange;

    @ApiModelProperty("高度范围")
    private BigDecimalRange heightRange;

    /**
     * 规则名称
     *
     * @return
     */
    @Override
    public String name() {
        return LogisticsRule.RULE + "商品尺寸规则";
    }
}
