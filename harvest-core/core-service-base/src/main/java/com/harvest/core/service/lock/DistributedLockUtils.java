package com.harvest.core.service.lock;

import com.harvest.core.service.redis.prefix.BaseKeyPrefix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 1:05 PM
 * @Description: 分布式锁工具类
 **/
public class DistributedLockUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedLockUtils.class);

    public static <T extends BaseKeyPrefix, V> V lock(T lockKey, Callable<V> callable) throws Exception {
        if (lockKey == null) {
            throw new RuntimeException("分布式锁键值不能为空");
        }
        return lock(lockKey.getKeyPrefix(), callable, lockKey.expireSeconds());
    }

    public static <T extends BaseKeyPrefix, V> V lock(T lockKey, Callable<V> callable, int seconds) throws Exception {
        if (lockKey == null) {
            throw new RuntimeException("分布式锁键值不能为空");
        }
        return lock(lockKey.getKeyPrefix(), callable, seconds);
    }

    public static <V> V lock(String lockKey, Callable<V> callable, int seconds) throws Exception {
        return RedissonLockUtils.lock(lockKey, callable, seconds);
    }

}
