package com.harvest.wms.repository.domain.warehouse.simple;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.harvest.wms.repository.domain.warehouse.WarehouseCore;
import com.harvest.wms.repository.domain.warehouse.base.WarehouseAddress;
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

    private WarehouseAddress warehouseAddress;

    private Integer status;

    @TableLogic
    private Boolean isDeleted;

}
