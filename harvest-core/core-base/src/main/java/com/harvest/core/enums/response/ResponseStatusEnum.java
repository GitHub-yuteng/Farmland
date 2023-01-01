package com.harvest.core.enums.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ResponseStatusEnum {

    /**
     * 请求详情服务状态码
     */
    SUCCESS (200, "success"),
    FAIL    (500, "fail");

    private final Integer code;
    private final String describe;

}
