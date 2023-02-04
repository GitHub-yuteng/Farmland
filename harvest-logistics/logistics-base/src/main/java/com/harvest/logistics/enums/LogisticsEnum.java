package com.harvest.logistics.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/2/4 2:42 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum LogisticsEnum {

    /**
     * 物流枚举
     */
    JD("JD", "京东", "京东物流", 1),

    ;


    private final String code;

    private final String name;

    private final String desc;

    private final Integer index;

}
