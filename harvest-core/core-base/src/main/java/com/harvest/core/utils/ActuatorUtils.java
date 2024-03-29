package com.harvest.core.utils;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.batch.BatchId;
import com.harvest.core.constants.GlobalMacroDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2022/12/12 9:30 PM
 * @Description: 并发执行工具集
 **/
public class ActuatorUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActuatorUtils.class);

    private static final int DEFAULT_PARTITION_SIZE = 200;

    private static volatile ThreadPoolExecutor PARALLEL_EXECUTOR_POOL = null;

    private static final int MAX_POOL_THREAD = 50;


    /**
     * 创建线程池
     */
    private static void initThreadPool() {
        if (PARALLEL_EXECUTOR_POOL != null) {
            return;
        }

        synchronized (ActuatorUtils.class) {
            if (PARALLEL_EXECUTOR_POOL == null) {
                PARALLEL_EXECUTOR_POOL = new ThreadPoolExecutor(
                        MAX_POOL_THREAD,
                        MAX_POOL_THREAD,
                        2000,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(),
                        new ThreadFactoryBuilder().build(),
                        new ThreadPoolExecutor.AbortPolicy());
                PARALLEL_EXECUTOR_POOL.allowCoreThreadTimeOut(true);
            }
        }
    }


    /**
     * 串行可失败处理器-范型限定
     *
     * @param collection 集合
     * @param consumer   消费方式
     * @param keyGetter  主键获取 如果不传，则集合值作为主键
     * @param <E>        集合泛型
     * @param <T>        主键泛型
     * @return 执行结果
     */
    public static <E extends BatchId, T> BatchExecuteResult<T> failAllowBatchExecute(Collection<E> collection, Consumer<E> consumer, Function<E, T> keyGetter) {
        if (CollectionUtils.isEmpty(collection)) {
            return new BatchExecuteResult<>();
        }
        BatchExecuteResult<T> result = new BatchExecuteResult<>(collection.size());
        collection.forEach(getConsumer(result, consumer, keyGetter));
        return result;
    }

    /**
     * 并发批量处理器-范型限定
     *
     * @param collection
     * @param consumer
     * @param <E>
     * @return
     */
    public static <E extends BatchId> void parallelVoidFailAllowBatchExecute(Collection<E> collection, Consumer<E> consumer) {
        parallelFailAllowBatchExecute(collection, consumer, Function.identity());
    }

    /**
     * 并发批量处理器-范型限定
     *
     * @param collection
     * @param consumer
     * @param <E>
     * @return
     */
    public static <E extends BatchId> BatchExecuteResult<E> parallelFailAllowBatchExecute(Collection<E> collection, Consumer<E> consumer) {
        return parallelFailAllowBatchExecute(collection, consumer, Function.identity());
    }

    /**
     * 并发批量处理器-范型限定
     * <p>
     * 多个任务执行，允许其中有任务失败。并返回失败记录的错误原因
     * 比如 修改10个订单的状态，其中有一个订单的状态不能被修改，
     * 则其他9个修改成功，并返回失败的这个的错误原因
     */
    public static <E extends BatchId, T> BatchExecuteResult<T> parallelFailAllowBatchExecute(Collection<E> collection, Consumer<E> consumer, Function<E, T> keyGetter) {
        if (CollectionUtils.isEmpty(collection)) {
            return new BatchExecuteResult<>();
        }
        BatchExecuteResult<T> result = new BatchExecuteResult<>(collection.size());
        //初始化线程池
        initThreadPool();
        CountDownLatch latch = new CountDownLatch(collection.size());
        collection.forEach(c -> PARALLEL_EXECUTOR_POOL.submit(() -> {
            try {
                getConsumer(result, consumer, keyGetter).accept(c);
            } finally {
                latch.countDown();
            }
        }));
        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.error("并发批量处理中断异常", e);
            return result;
        }
        return result;
    }

    private static <E extends BatchId, T> Consumer<E> getConsumer(BatchExecuteResult<T> result, Consumer<E> consumer, Function<E, T> keyGetter) {
        return c -> {
            try {
                consumer.accept(c);
                BatchExecuteResult.ReasonMap<T> map = new BatchExecuteResult.ReasonMap<>();
                try {
                    // 使用范型限定取出业务 Id
                    map.setId((c.getId()));
                    map.setKey(keyGetter.apply(c));
                    map.setReason(GlobalMacroDefinition.ParameterNames.SUCCESS);
                } catch (Exception key) {
                    LOGGER.error("可失败任务键值key取值异常!", key);
                }
                result.getSuccessList().add(map);
                result.getSuccessCount().incrementAndGet();
            } catch (Exception e) {
                LOGGER.info("可失败任务处理异常", e);
                BatchExecuteResult.ReasonMap<T> map = new BatchExecuteResult.ReasonMap<>();
                try {
                    // 使用范型限定取出业务 Id
                    map.setId((c.getId()));
                    map.setKey(keyGetter.apply(c));
                    map.setReason(e.getMessage());
                    map.setE(e);
                } catch (Exception key) {
                    LOGGER.error("可失败任务键值key取值异常!", key);
                }
                result.getErrorList().add(map);
                result.getFailCount().incrementAndGet();
            }
        };
    }

    /**
     * 分批执行器。
     * 比如需要查询一万个id对应的数据。如果一次性查询的话可能导致查询效率低或者直接报错（ sql 中in条件可能存在条数限制 ）
     * 可以使用这个分批查询，比如一次查询1000条，执行10次，全部完成后执行结果分装
     *
     * @param list          原集合
     * @param partitionSize 分区大小
     * @param function      执行的操作
     * @param <E>           输入泛型
     * @param <T>           输出泛型
     * @return 结果集
     */
    public static <E, T> List<T> partitionExecute(List<E> list, int partitionSize, Function<List<E>, Collection<T>> function) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        if (partitionSize == 0) {
            partitionSize = DEFAULT_PARTITION_SIZE;
        }
        return Lists.partition(list, partitionSize).stream().flatMap(l -> function.apply(l).stream()).collect(Collectors.toList());
    }
}
