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
    SHOP                (1, "店铺平台"),
    WAREHOUSE           (2, "仓库平台"),
    LOGISTICS           (3, "承运商"),
    LOGISTICS_TRACKING  (4, "物流追踪商");

    /**
     * 类型
     */
    public final Integer type;

    /**
     * 标签
     */
    public final String label;

}
