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
