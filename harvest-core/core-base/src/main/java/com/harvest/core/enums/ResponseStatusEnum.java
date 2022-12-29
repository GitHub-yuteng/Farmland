package com.harvest.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: TODO
 * @Author yt
 * @Date 2021/12/28 11:07 上午
 */
@Getter
@AllArgsConstructor
public enum ResponseStatusEnum {

    /**
     * 请求详情服务状态码
     */
    SUCCESS             (200, "success"),
    FAIL                (500, "fail");

    private final Integer code;
    private final String describe;

}
