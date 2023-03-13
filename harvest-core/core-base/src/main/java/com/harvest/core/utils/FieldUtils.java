package com.harvest.core.utils;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    private static final String GET = "get";

    enum FieldSwitchEnum {
        /**
         * 默认获取、转大写、转小写
         */
        DEFAULT, UPPERCASE, LOWERCASE
    }

    public static <T> String getFieldName(SFunction<T, ?> fn) {
        return getFieldName(fn, FieldSwitchEnum.DEFAULT);
    }

    public static <T> String getFieldName(SFunction<T, ?> fn, FieldSwitchEnum fieldSwitch) {
        return getSwitchFieldName(fn, fieldSwitch);
    }

    /**
     *
     * @param fn
     * @param fieldSwitch
     * @param <T>
     * @return
     */
    private static <T> String getSwitchFieldName(SFunction<T, ?> fn, FieldSwitchEnum fieldSwitch) {
        SerializedLambda serializedLambda = getSerializedLambda(fn);

        // 从lambda信息取出method、field、class等
        String fieldName = serializedLambda.getImplMethodName().substring(GET.length());
        fieldName = fieldName.replaceFirst(String.valueOf(fieldName.charAt(0)), String.valueOf(fieldName.charAt(0)).toLowerCase());
        switch (fieldSwitch) {
            case UPPERCASE:
                return fieldName.toUpperCase();
            case LOWERCASE:
                return fieldName.toLowerCase();
            default:
                return fieldName;
        }
    }

    private static <T> SerializedLambda getSerializedLambda(SFunction<T, ?> fn) {
        // 从function取出序列化方法
        Method writeReplaceMethod;
        try {
            writeReplaceMethod = fn.getClass().getDeclaredMethod("writeReplace");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        // 从序列化方法取出序列化的lambda信息
        boolean isAccessible = writeReplaceMethod.isAccessible();
        writeReplaceMethod.setAccessible(true);
        SerializedLambda serializedLambda;
        try {
            serializedLambda = (SerializedLambda) writeReplaceMethod.invoke(fn);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        writeReplaceMethod.setAccessible(isAccessible);
        return serializedLambda;
    }

}
