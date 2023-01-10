package com.harvest.wms.repository.domain;

import com.harvest.wms.repository.domain.warehouse.simple.WarehouseSimplePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 3:55 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class WarehouseDO extends WarehouseSimplePO {

    private static final long serialVersionUID = -4382136550117360785L;

}
