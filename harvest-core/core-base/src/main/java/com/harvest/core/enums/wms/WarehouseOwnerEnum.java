package com.harvest.core.enums.wms;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2022/12/18 5:03 PM
 * @Description: 仓库所属类型
 **/
@Getter
@AllArgsConstructor
public enum WarehouseOwnerEnum {

    /**
     * 仓库所属类型
     */
    OWN         (10, "商家自有仓库"),
    FULFILLMENT (20, "平台履约仓"),
    THIRD       (30, "三方仓库/海外仓");

    private final Integer type;
    private final String value;

}
