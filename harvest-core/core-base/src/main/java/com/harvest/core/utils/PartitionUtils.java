package com.harvest.core.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/2 11:42 PM
 * @Description: 分区优化工具集
 **/
public class PartitionUtils {

    private final static Integer DEFAULT_PARTITION_SIZE = 200;

    /**
     * 分批执行器: 比如需要查询1w条数据。如果一次性查询的话可能导致查询效率低或者直接报错（ sql 中in条件可能存在条数限制 ）
     * 可以使用分批查询，比如一次查询 [partitionSize] 条, 全部完成后返回执行结果
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

    /**
     * 分批执行器: 比如需要查询1w条数据。如果一次性查询的话可能导致查询效率低或者直接报错（ sql 中in条件可能存在条数限制 ）
     * 可以使用分批查询，比如一次查询 [partitionSize] 条
     * 并且每个Id都查询出了一组数据进行Map分组
     * 订单 -> 订单明细
     *
     * @param list          原集合
     * @param partitionSize 分区大小
     * @param function      执行的操作
     * @param <E>           输入泛型
     * @param <T>           输出泛型
     * @return 结果集
     */
    public static <E, T> Map<E, List<T>> partitionMapExecute(List<E> list, Integer partitionSize, Function<List<E>, Map<E, List<T>>> function) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        if (Objects.isNull(partitionSize)) {
            partitionSize = DEFAULT_PARTITION_SIZE;
        }
        if (list.size() < partitionSize) {
            return function.apply(list);
        }

        Map<E, List<T>> resultMap = Maps.newHashMap();
        Lists.partition(list, partitionSize).forEach(partition -> {
            Map<E, List<T>> apply = function.apply(partition);
            resultMap.putAll(apply);
        });
        return resultMap;
    }
}
