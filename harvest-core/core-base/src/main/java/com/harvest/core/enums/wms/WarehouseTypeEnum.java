package com.harvest.core.enums.wms;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2022/12/29 2:56 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum WarehouseTypeEnum {

    /**
     * 仓库类型
     */
    NORMAL      (1, "正品仓"),
    DEFECTIVE   (2, "次品仓"),
    STOCK       (3, "存货仓"),
    STORE       (4, "门店仓");

    public final int type;
    public final String value;
}
