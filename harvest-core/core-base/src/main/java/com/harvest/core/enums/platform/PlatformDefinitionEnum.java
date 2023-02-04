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
    WAREHOUSE           (1, "仓库平台"),
    LOGISTICS           (2, "承运商"),
    LOGISTICS_TRACK     (3, "物流追踪商");

    /**
     * 类型
     */
    public final Integer type;

    /**
     * 标签
     */
    public final String label;

}
