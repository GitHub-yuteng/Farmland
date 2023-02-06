package com.harvest.core.enums.platform;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/1/31 9:33 AM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum PlatformDefinitionEnum {

    /**
     * 平台定义
     */
    PLATFORM                (1, "平台"),
    LOGISTICS               (2, "承运商"),
    LOGISTICS_TRACK         (3, "物流追踪商"),
    WAREHOUSE               (4, "仓储服务商"),

    CROSS_PLATFORM          (11, "跨境平台"),
    CROSS_LOGISTICS         (12, "跨境承运商"),
    CROSS_LOGISTICS_TRACK   (13, "跨境物流追踪商"),
    CROSS_WAREHOUSE         (14, "跨境仓储服务商"),
    ;

    /**
     * 类型
     */
    public final Integer type;

    /**
     * 标签
     */
    public final String label;

}
