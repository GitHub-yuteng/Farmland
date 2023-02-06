package com.harvest.core.enums.response;

import com.harvest.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ResponseStatusEnum implements IEnum<Integer> {

    /**
     * 请求详情服务状态码
     */
    SUCCESS (200, "success"),
    FAIL    (580, "fail");

    private final Integer code;
    private final String describe;

    @Override
    public Integer getKey() {
        return this.code;
    }
}
