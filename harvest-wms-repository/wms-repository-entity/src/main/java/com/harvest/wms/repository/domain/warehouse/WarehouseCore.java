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

    @ApiModelProperty("仓库编码")
    private String warehouseCode;

    @ApiModelProperty("仓库名称")
    private String warehouse;

    @ApiModelProperty("仓库所属")
    private WarehouseOwnerEnum warehouseOwner;

    @ApiModelProperty("仓库类型")
    private WarehouseTypeEnum warehouseType;

    @ApiModelProperty("仓库状态")
    private Integer status;

}
