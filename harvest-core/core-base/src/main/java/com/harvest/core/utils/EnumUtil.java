package com.harvest.core.utils;

import com.harvest.core.enums.IEnum;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.*;

public class EnumUtil {

    /**
     * 将desc字符串转换为Enum
     *
     * @param clazz 枚举Class
     * @param type  枚举描述值
     * @param <E>   枚举类型
     * @return 转换后的Enum
     */
    public static <E extends Enum<?> & IEnum<?>> E convert(Class<E> clazz, String type) {
        Assert.notNull(clazz, "枚举类型不能为空");
        E[] enumConstants = clazz.getEnumConstants();
        for (E e : enumConstants) {
            if (String.valueOf(e.getKey()).equals(type)) {
                return e;
            }
        }
        return null;
    }

    public static <E extends Enum<?> & IEnum<?>> Set<E> convertList(Class<E> clazz, String types) {
        Assert.notNull(clazz, "枚举类型不能为空");
        if (StringUtils.isEmpty(types)) {
            return null;
        }
        Set<E> result = new HashSet<>();
        E[] enumConstants = clazz.getEnumConstants();
        List<String> enums = JsonUtils.json2ObjectList(types, String.class);
        assert enums != null;
        for (String type : enums) {
            for (E e : enumConstants) {
                if (String.valueOf(e.getKey()).equals(type)) {
                    result.add(e);
                }
            }
        }
        return result;
    }

    /**
     * 把字符串转换为传入的枚举类型，若转换失败，则返回default。
     *
     * @param enumClass  需要处理的枚举类
     * @param value      传入的字符串
     * @param defaultVal 取不到的话返回的默认值
     * @param <E>        泛型
     * @return 若匹配到则返回对应的枚举值，否则返回默认值
     */
    public static <E extends Enum<E>> E enumValue(Class<E> enumClass, String value, E defaultVal) {
        try {
            return Enum.valueOf(enumClass, value);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /***
     * 判断枚举中是否有该值
     * @param enumClass 枚举类
     * @param value 需要检查的字符串值
     * @param <E> 泛型
     * @return true表示有，false表示没有该值
     */
    public static <E extends Enum<E>> boolean enumHasValue(Class<E> enumClass, String value) {
        return EnumUtils.isValidEnum(enumClass, value);
    }

    /**
     * 判断枚举值是否在集合中
     */
    public static <E extends Enum<E>> boolean containsValue(Collection<E> enumCollection, E value) {
        Assert.notEmpty(enumCollection, "请提供枚举集合");
        Optional<E> any = enumCollection.stream().filter(e -> e == value).findAny();
        return any.isPresent();
    }

    public static <E extends Enum<E>> boolean containsValue(E[] enumCollection, E value) {
        Assert.notEmpty(enumCollection, "请提供枚举集合");
        return containsValue(Arrays.asList(enumCollection), value);
    }

}
