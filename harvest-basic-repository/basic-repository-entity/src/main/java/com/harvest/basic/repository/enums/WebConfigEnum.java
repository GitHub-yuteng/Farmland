package com.harvest.basic.repository.enums;

import com.harvest.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/1/8 11:46 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum WebConfigEnum implements IEnum<String> {

    /**
     * 前端配置枚举
     */
    layout("layout", "布局");

    private final String type;

    private final String describe;

    @Override
    public String getKey() {
        return this.type;
    }
}
