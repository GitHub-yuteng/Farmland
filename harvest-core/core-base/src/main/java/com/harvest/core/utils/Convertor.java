package com.harvest.core.utils;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2022/12/12 10:48 PM
 * @Description: 转换器
 **/
@FunctionalInterface
public interface Convertor<E, D> {

    /**
     * 转换list
     *
     * @param sources 原集合
     * @return 结果集合
     */
    default Collection<D> convertList(Collection<E> sources) {
        if (CollectionUtils.isEmpty(sources)) {
            return Collections.emptyList();
        }
        return sources.stream().map(this::convert).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 单例转换
     *
     * @param source 待转换对象
     * @return 已转换对象
     */
    D convert(E source);
}
