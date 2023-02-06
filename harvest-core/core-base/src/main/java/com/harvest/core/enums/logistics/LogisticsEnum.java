package com.harvest.core.enums.logistics;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/2/4 2:42 PM
 * @Description:
 **/
@Getter
@AllArgsConstructor
public enum LogisticsEnum {

    /**
     * 物流枚举
     */
    JD      ("JD", "京东", "京东物流", 1),
    SF      ("SF", "顺丰", "顺丰", 2),
    CAINIAO ("CAINIAO", "菜鸟", "菜鸟", 3),

    ;


    private final String code;

    private final String name;

    private final String desc;

    /**
     * 排序&服务类型
     */
    private final Integer index;

    public LogisticsEnum getEnumByIndex(Integer index) {
        for (LogisticsEnum logisticsEnum : LogisticsEnum.values()) {
            if (logisticsEnum.getIndex().equals(index)) {
                return logisticsEnum;
            }
        }
        return null;
    }

}
