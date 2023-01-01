package com.harvest.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2022/12/30 4:48 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum WeightEnum implements IEnum<Integer> {

    /**
     * 重量枚举
     */
    KG  (1, "kg", 1,          "千克(kg)"),
    G   (2, "g",  1000,       "克(g)"),
    MG  (3, "mg", 1000 * 1000,"毫克(mg)");

    public final int type;

    public final String value;

    public final double rate;

    public final String label;

    @Override
    public Integer getKey() {
        return this.type;
    }

}
