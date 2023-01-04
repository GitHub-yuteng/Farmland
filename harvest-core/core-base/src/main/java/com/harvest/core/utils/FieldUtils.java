package com.harvest.core.utils;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @Author: Alodi
 * @Date: 2023/1/4 11:58 PM
 * @Description: 对象字段反射工具类
 **/
public class FieldUtils {

    /**
     * 使Function获取序列化能力
     */
    @FunctionalInterface
    public interface SFunction<T, R> extends Function<T, R>, Serializable {
    }

}
