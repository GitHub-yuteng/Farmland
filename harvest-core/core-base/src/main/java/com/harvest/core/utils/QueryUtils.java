package com.harvest.core.utils;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/2 11:42 PM
 * @Description: 查询优化工具集
 **/
public class QueryUtils {

    private final static Integer DEFAULT_PARTITION_SIZE = 200;

    /**
     * 分批执行器: 比如需要查询1w条数据。如果一次性查询的话可能导致查询效率低或者直接报错（ sql 中in条件可能存在条数限制 ）
     * 可以使用这个分批查询，比如一次查询 [partitionSize] 条, 全部完成后返回执行结果
     *
     * @param list          原集合
     * @param partitionSize 分区大小
     * @param function      执行的操作
     * @param <E>           输入泛型
     * @param <T>           输出泛型
     * @return 结果集
     */
    public static <E, T> List<T> partitionExecute(List<E> list, Integer partitionSize, Function<List<E>, List<T>> function) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        if (Objects.isNull(partitionSize)) {
            partitionSize = DEFAULT_PARTITION_SIZE;
        }
        if (list.size() < partitionSize) {
            return function.apply(list);
        }
        return Lists.partition(list, partitionSize).stream().flatMap(f -> function.apply(f).stream()).collect(Collectors.toList());
    }
}
