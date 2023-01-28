package com.harvest.core.enums.wms;

import com.harvest.core.enums.IEnum;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2022/12/29 2:56 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum WarehouseTypeEnum implements IEnum<Integer> {

    /**
     * 仓库类型
     */
    NORMAL      (1, "正品仓"),
    DEFECTIVE   (2, "次品仓"),
    STOCK       (3, "存货仓"),
    STORE       (4, "门店仓");

    private final Integer type;
    private final String value;

    public static WarehouseTypeEnum getEnumByType(int type) {
        for (WarehouseTypeEnum warehouseTypeEnum : WarehouseTypeEnum.values()) {
            if (warehouseTypeEnum.type.equals(type)) {
                return warehouseTypeEnum;
            }
        }
        return null;
    }

    @Override
    public Integer getKey() {
        return this.type;
    }
}
