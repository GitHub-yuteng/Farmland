package com.harvest.wms.repository.domain.warehouse.core;

import com.harvest.core.domain.CompanyId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2023/1/4 3:39 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class WarehouseCore extends CompanyId implements Serializable {

    private static final long serialVersionUID = 1835434980908009662L;

}
