package com.harvest.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2022/12/30 4:46 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum LengthEnum implements IEnum<Integer> {

    /**
     *
     */
    m   (1, "m",  1,           "米(m)"),
    dm  (2, "dm", 10,          "分米(dm)"),
    cm  (3, "cm", 10 * 10,     "厘米(cm)"),
    mm  (4, "mm", 10 * 10 * 10,"毫米(mm)");

    public final int type;

    public final String value;

    public final double rate;

    public final String label;

    @Override
    public Integer getKey() {
        return this.type;
    }

}
