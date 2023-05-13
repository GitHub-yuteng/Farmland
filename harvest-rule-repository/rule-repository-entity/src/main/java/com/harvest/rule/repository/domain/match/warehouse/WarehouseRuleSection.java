package com.harvest.rule.repository.domain.match.warehouse;

import com.harvest.rule.repository.domain.match.warehouse.section.WarehouseAddressRuleSection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 11:23 AM
 * @Description: TODO
 **/
@Data
public class WarehouseRuleSection {

    @ApiModelProperty("地址规则")
    private WarehouseAddressRuleSection warehouseAddressRuleSection;


}
