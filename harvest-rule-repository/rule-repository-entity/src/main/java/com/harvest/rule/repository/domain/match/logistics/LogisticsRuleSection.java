package com.harvest.rule.repository.domain.match.logistics;

import com.harvest.rule.repository.domain.match.logistics.section.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 11:23 AM
 * @Description: TODO
 **/
@Data
public class LogisticsRuleSection {

    @ApiModelProperty("国家规则")
    private LogisticsCountryRuleSection countryRuleSection;

    @ApiModelProperty("商品尺寸规则")
    private LogisticsGoodsSizeRuleSection goodsSizeRuleSection;

    @ApiModelProperty("订单金额规则")
    private LogisticsOrderAmountRuleSection orderAmountRuleSection;

    @ApiModelProperty("包裹信息规则")
    private LogisticsPackageRuleSection packageRuleSection;

    @ApiModelProperty("仓库信息规则")
    private LogisticsWarehouseRuleSection warehouseRuleSection;
}
