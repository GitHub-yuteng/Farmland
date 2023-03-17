package com.harvest.oms.domain.warehouse;

import com.harvest.core.domain.CompanyId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: Alodi
 * @Date: 2023/1/10 10:58 PM
 * @Description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WarehouseKey extends CompanyId {

    private static final long serialVersionUID = -7276876593587468186L;

    @ApiModelProperty("仓库Id")
    private Long warehouseId;

    public WarehouseKey(Long companyId, Long warehouseId) {
        super(companyId);
        this.warehouseId = warehouseId;
    }

    public static WarehouseKey build(Long companyId, Long warehouseId) {
        WarehouseKey warehouseKey = new WarehouseKey();
        warehouseKey.setCompanyId(companyId);
        warehouseKey.setWarehouseId(warehouseId);
        return warehouseKey;
    }
}
