package com.harvest.oms.repository.query.order.pack;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/18 5:01 PM
 * @Description: TODO
 **/
@Data
public class OrderWarehouseQuery implements Serializable {

    private static final long serialVersionUID = 1024398785906197092L;

    /**
     * {@link com.harvest.core.enums.wms.WarehouseOwnerEnum}
     */
    @ApiModelProperty("仓库所属类型")
    private Integer warehouseOwner;

    @ApiModelProperty("仓库Id")
    private Collection<Long> warehouseIds;
}
