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

    public static <T extends BaseKeyPrefix, V> V lock(T keyPrefix, String lockKey, Callable<V> callable) throws Exception {
        if (Objects.isNull(keyPrefix) || StringUtils.isEmpty(lockKey)) {
            throw new RuntimeException("Distributed lock key value cannot be empty.");
        }
        return lock(keyPrefix.getKeyPrefix() + lockKey, callable, keyPrefix.expireSeconds());
    }

    public static <T extends BaseKeyPrefix, V> V lock(T keyPrefix, String lockKey, Callable<V> callable, int seconds) throws Exception {
        if (Objects.isNull(keyPrefix) || StringUtils.isEmpty(lockKey)) {
            throw new RuntimeException("Distributed lock key value cannot be empty.");
        }
        return lock(keyPrefix.getKeyPrefix() + lockKey, callable, seconds);
    }

    public static <V> V lock(String realKey, Callable<V> callable, int seconds) throws Exception {
        if (StringUtils.isEmpty(realKey)) {
            throw new RuntimeException("Distributed lock key value cannot be empty.");
        }
        return RedissonLockUtils.lock(realKey, callable, seconds);
    }
}
