package com.harvest.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/2/20 9:17 AM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum SwitchEnum implements IEnum<Integer> {

    /* 开 */
    ON(1),
    /* 关 */
    OFF(0);

    private final Integer status;

    /**
     * 获取枚举唯一值
     *
     * @return 唯一值
     */
    @Override
    public Integer getKey() {
        return this.getStatus();
    }
}
