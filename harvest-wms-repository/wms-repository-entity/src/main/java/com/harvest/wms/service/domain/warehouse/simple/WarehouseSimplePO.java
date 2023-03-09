package com.harvest.wms.service.domain.warehouse.simple;

import com.harvest.wms.service.domain.warehouse.WarehouseCore;
import com.harvest.wms.service.domain.warehouse.base.WarehouseAddress;
import com.harvest.wms.service.domain.warehouse.base.WarehouseSwitch;
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
public class WarehouseSimplePO extends WarehouseCore {

    private static final long serialVersionUID = -2805112155380766528L;

    private Integer mainType;

    private Boolean isDelivery;

    @ApiModelProperty("仓库地址")
    private WarehouseAddress warehouseAddress;

    @ApiModelProperty("仓库配置开关")
    private WarehouseSwitch warehouseSwitch;

}
