package com.harvest.core.utils;

import cn.hutool.core.util.ObjectUtil;

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

    /**
     * 克隆对象
     * 如果对象实现Cloneable接口，调用其clone方法
     * 如果实现Serializable接口，执行深度克隆
     * 否则返回 null
     *
     * @param <T> 对象类型
     * @param obj 被克隆对象
     * @return 克隆后的对象
     */
    public static <T> T clone(T obj) {
        return ObjectUtil.clone(obj);
    }
}
