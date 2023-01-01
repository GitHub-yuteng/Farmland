package com.harvest.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2022/12/30 4:50 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum VolumeEnum implements IEnum<Integer> {

    /**
     * 体积枚举
     */
    m3  (1, "m³", 1,        "立方米(m³)"),
    dm3 (2, "dm³",1000,     "立方分米(dm³)"),
    cm3 (3, "cm³",1000*1000,"立方厘米(cm³)");

    public final int type;

    public final String value;

    public final double rate;

    public final String label;

    @Override
    public Integer getKey() {
        return this.type;
    }

}
