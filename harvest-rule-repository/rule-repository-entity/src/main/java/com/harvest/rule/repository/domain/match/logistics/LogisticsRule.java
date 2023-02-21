package com.harvest.rule.repository.domain.match.logistics;


import com.harvest.core.rule.confine.Rule;
import com.harvest.rule.repository.domain.match.logistics.section.*;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty("国家规则")
    private CountryRuleSection countryRule;

    @ApiModelProperty("商品尺寸规则")
    private GoodsSizeRuleSection goodsSizeRuleSection;

    @ApiModelProperty("订单金额规则")
    private OrderAmountRuleSection orderAmountRuleSection;

    @ApiModelProperty("包裹信息规则")
    private PackageRuleSection packageRuleSection;

    @ApiModelProperty("仓库信息规则")
    private WarehouseRuleSection warehouseRuleSection;

}
