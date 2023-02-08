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
    JD      (1, "京东"),
    Taobao  (2, "淘宝"),
    TikTok  (3, "TikTok"),
    TMall   (4, "TMall"),

    ;

    /**
     * 类型
     */
    private final int type;

    /**
     * 值
     */
    private final String value;

    @Override
    public Integer getKey() {
        return this.type;
    }
}
