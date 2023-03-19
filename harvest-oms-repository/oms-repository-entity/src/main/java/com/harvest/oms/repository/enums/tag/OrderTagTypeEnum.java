package com.harvest.oms.repository.enums.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/2/1 3:27 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum OrderTagTypeEnum {

    /**
     * 标记类型
     */
    ORDER       (1),
    ORDER_ITEM  (2);

    private final Integer value;
}
