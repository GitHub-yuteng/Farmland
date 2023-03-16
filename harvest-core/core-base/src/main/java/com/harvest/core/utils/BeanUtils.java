package com.harvest.core.utils;

import java.util.function.Supplier;

/**
 * @Author: Alodi
 * @Date: 2023/3/16 2:56 PM
 * @Description: TODO
 **/
public class BeanUtils {

    /**
     * 多级调用避免空指针
     *
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> T getNullable(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            // skip
            return null;
        }
    }
}
