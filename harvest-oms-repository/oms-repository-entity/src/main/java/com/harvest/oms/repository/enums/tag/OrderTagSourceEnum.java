package com.harvest.oms.repository.enums.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/2/1 3:28 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum OrderTagSourceEnum {

    /**
     * 订单标记类型
     */
    SYSTEM      (10000),
    PLATFORM    (20000),
    CUSTOM      (30000),

    ;

    private final Integer value;

    /**
     * 根据值判定来源
     *
     * @param value
     * @return
     */
    public static OrderTagSourceEnum calcTag(Integer value) {
        for (OrderTagSourceEnum orderTagSourceEnum : OrderTagSourceEnum.values()) {
            if (value > orderTagSourceEnum.value) {
                return orderTagSourceEnum;
            }
        }
        return null;
    }

}
