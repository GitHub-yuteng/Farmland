package com.harvest.wms.repository.service.domain.warehouse.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 2:05 AM
 * @Description: TODO
 **/
@Data
public class WarehouseSwitch implements Serializable {

    private static final long serialVersionUID = -5650677554760895170L;

    @ApiModelProperty("仓库Id")
    private Long warehouseId;

    @ApiModelProperty("奇门对接")
    private Boolean openQimen;

    @ApiModelProperty("自动同步库存")
    private Boolean openAutoSync;

}
