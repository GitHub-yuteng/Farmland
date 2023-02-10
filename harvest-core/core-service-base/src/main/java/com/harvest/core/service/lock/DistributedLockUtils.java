package com.harvest.core.service.lock;

import com.harvest.core.service.redis.prefix.BaseKeyPrefix;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 1:05 PM
 * @Description: 分布式锁工具类
 **/
public class DistributedLockUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedLockUtils.class);

    /**
     * Runnable lock.
     *
     * @param keyPrefix
     * @param lockKey
     * @param runnable
     * @param <T>
     */
    public static <T extends BaseKeyPrefix> void lock(T keyPrefix, String lockKey, Runnable runnable) {
        if (Objects.isNull(keyPrefix) || StringUtils.isEmpty(lockKey)) {
            throw new RuntimeException("Distributed lock key value cannot be empty.");
        }
        lock(keyPrefix.getKeyLock(), lockKey, runnable, keyPrefix.expireSeconds());
    }

    /**
     * Runnable lock.
     *
     * @param keyPrefix
     * @param lockKey
     * @param runnable
     * @param seconds
     * @param <T>
     */
    public static <T extends BaseKeyPrefix> void lock(T keyPrefix, String lockKey, Runnable runnable, int seconds) {
        if (Objects.isNull(keyPrefix) || StringUtils.isEmpty(lockKey)) {
            throw new RuntimeException("Distributed lock key value cannot be empty.");
        }
        lock(keyPrefix.getKeyLock(), lockKey, runnable, seconds);
    }

    /**
     * Runnable lock.
     *
     * @param realKey
     * @param lockKey
     * @param runnable
     * @param seconds
     */
    private static void lock(String realKey, String lockKey, Runnable runnable, int seconds) {
        RedissonLockUtils.lock(realKey + lockKey, runnable, seconds);
    }

    /**
     * Callable lock.
     *
     * @param keyPrefix
     * @param lockKey
     * @param callable
     * @param <T>
     * @param <V>
     * @return
     * @throws Exception
     */
    public static <T extends BaseKeyPrefix, V> V lock(T keyPrefix, String lockKey, Callable<V> callable) throws Exception {
        if (Objects.isNull(keyPrefix) || StringUtils.isEmpty(lockKey)) {
            throw new RuntimeException("Distributed lock key value cannot be empty.");
        }
        return lock(keyPrefix.getKeyLock() + lockKey, callable, keyPrefix.expireSeconds());
    }

    /**
     * Callable lock.
     *
     * @param keyPrefix
     * @param lockKey
     * @param callable
     * @param seconds
     * @param <T>
     * @param <V>
     * @return
     * @throws Exception
     */
    public static <T extends BaseKeyPrefix, V> V lock(T keyPrefix, String lockKey, Callable<V> callable, int seconds) throws Exception {
        if (Objects.isNull(keyPrefix) || StringUtils.isEmpty(lockKey)) {
            throw new RuntimeException("Distributed lock key value cannot be empty.");
        }
        return lock(keyPrefix.getKeyLock() + lockKey, callable, seconds);
    }

    /**
     * Callable lock.
     *
     * @param realKey
     * @param callable
     * @param seconds
     * @param <V>
     * @return
     * @throws Exception
     */
    private static <V> V lock(String realKey, Callable<V> callable, int seconds) throws Exception {
        if (StringUtils.isEmpty(realKey)) {
            throw new RuntimeException("Distributed lock key value cannot be empty.");
        }
        return RedissonLockUtils.lock(realKey, callable, seconds);
    }
}
