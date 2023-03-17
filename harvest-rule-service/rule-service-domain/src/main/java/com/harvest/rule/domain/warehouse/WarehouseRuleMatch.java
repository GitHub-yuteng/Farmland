package com.harvest.rule.domain.warehouse;

import com.harvest.core.domain.CompanyId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 9:25 AM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class WarehouseRuleMatch extends CompanyId {

    private static final long serialVersionUID = 6766012676080039561L;

    private Long warehouseId;

}
