package com.harvest.core.enums.oms;

import com.harvest.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:31 AM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum OrderSourceEnum implements IEnum<Integer> {

    /**
     *
     */
    JD(1, "京东"),

    ;

    /**
     * 状态值
     */
    private final int type;

    /**
     * 描述
     */
    private final String value;

    @Override
    public Integer getKey() {
        return this.type;
    }
}
