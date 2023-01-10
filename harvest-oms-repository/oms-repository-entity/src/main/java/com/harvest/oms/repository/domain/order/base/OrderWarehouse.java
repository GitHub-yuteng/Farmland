package com.harvest.oms.repository.domain.order.base;

import com.harvest.core.enums.wms.WarehouseOwnerEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/29 11:49 AM
 * @Description: TODO
 **/
@Data
public class OrderWarehouse implements Serializable {

    private static final long serialVersionUID = -8467402012599950766L;

    @ApiModelProperty(value = "履约仓库所有者", notes = "影响最终的履约流程")
    private WarehouseOwnerEnum warehouseOwner;

    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

}
