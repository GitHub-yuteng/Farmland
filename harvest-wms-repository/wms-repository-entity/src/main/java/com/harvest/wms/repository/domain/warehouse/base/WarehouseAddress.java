package com.harvest.wms.repository.domain.warehouse.base;

import com.harvest.core.domain.address.HarvestAddress;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 2:05 AM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class WarehouseAddress extends HarvestAddress {

    private static final long serialVersionUID = -5650677554760895170L;

    @ApiModelProperty("仓库Id")
    private Long warehouseId;

}
