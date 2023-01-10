package com.harvest.wms.repository.domain.warehouse;

import com.harvest.core.domain.CompanyId;
import com.harvest.core.enums.wms.WarehouseOwnerEnum;
import com.harvest.core.enums.wms.WarehouseTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/1/4 3:39 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class WarehouseCore extends CompanyId {

    private static final long serialVersionUID = 1835434980908009662L;

    @ApiModelProperty("仓库Id")
    private Long warehouseId;

    private String warehouseCode;

    private String warehouse;

    private WarehouseOwnerEnum warehouseOwner;

    private WarehouseTypeEnum warehouseType;

}
