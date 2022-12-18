/*
 *
 * @(#)IEnum.java   2021-5-20
 *
 * Copyright (c) 2011-2021 杭州湖畔网络技术有限公司
 * 保留所有权利
 * 本软件为杭州湖畔网络技术有限公司所有及包含机密信息，须遵守其相关许可证条款进行使用。
 * Copyright (c) 2011-2021 HUPUN Network Technology CO.,LTD.
 * All rights reserved.
 * This software is the confidential and proprietary information of HUPUN
 * Network Technology CO.,LTD("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with HUPUN.
 * Website：http://www.hupun.com
 *
 */

package com.harvest.core.enums;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harvest.core.utils.EnumUtil;

public interface IEnum<T> {

    /**
     * 获取枚举唯一值
     *
     * @return 唯一值
     */
    @JsonIgnore
    T getKey();

    /**
     * 根据key值转换
     *
     * @param <E>   对应枚举
     * @param clazz 类
     * @param t     key
     * @return 枚举
     */
    static <E extends Enum<?> & IEnum<?>, T> E keyOf(Class<E> clazz, T t) {
        return EnumUtil.convert(clazz, String.valueOf(t));
    }

    /**
     * 根据key值转换
     *
     * @param <E>   对应枚举
     * @param clazz 类
     * @param t     key
     * @param e     未找到时返回默认值
     * @return 枚举
     */
    static <E extends Enum<?> & IEnum<?>, T> E keyOf(Class<E> clazz, T t, E e) {
        E res = EnumUtil.convert(clazz, String.valueOf(t));
        return null == res ? e : res;
    }
}
