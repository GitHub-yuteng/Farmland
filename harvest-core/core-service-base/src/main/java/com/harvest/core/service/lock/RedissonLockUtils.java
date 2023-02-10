package com.harvest.core.service.lock;

import com.harvest.core.context.SpringHelper;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 1:20 PM
 * @Description: 分布式锁工具类 RedissonLock 实现
 **/
public class RedissonLockUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedissonLockUtils.class);

    /**
     * 尝试获取锁最大次数
     */
    private static final int MAX_GET_LOCK_TIMES = 100;

    /**
     * 最大持有锁时间,单位:分钟,默认10分钟
     */
    private static final int MAX_HOLD_LOCK_SECONDS = 60 * 10;

    /**
     * Runnable lock.
     *
     * @param lockKey
     * @param runnable
     * @param seconds
     */
    public static void lock(String lockKey, Runnable runnable, int seconds) {
        if (StringUtils.isEmpty(lockKey)) {
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, "分布式锁键值不能为空!");
        }
        lock(lockKey, runnable, seconds, MAX_HOLD_LOCK_SECONDS, 1);
    }

    /**
     * Runnable lock with tryTimes.
     *
     * @param lockKey
     * @param runnable
     * @param seconds
     * @param tryTimes
     */
    public static void lock(String lockKey, Runnable runnable, int seconds, int tryTimes) {
        if (StringUtils.isEmpty(lockKey)) {
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, "分布式锁键值不能为空");
        }
        lock(lockKey, runnable, seconds, MAX_HOLD_LOCK_SECONDS, tryTimes);
    }

    /**
     * Runnable lock run.
     *
     * @param lockKey
     * @param runnable
     * @param waitTime
     * @param leaseTime
     * @param tryTimes
     */
    private static void lock(String lockKey, Runnable runnable, long waitTime, long leaseTime, int tryTimes) {
        RLock lock = SpringHelper.getBean(RedissonClient.class).getLock(lockKey);
        try {
            if (getRedisLock(lock, waitTime, leaseTime, tryTimes)) {
                runnable.run();
            }
        } catch (Exception e) {
            LOGGER.error("分布式锁 键值：{} 排队超时。超时时间:{} 秒", lockKey, waitTime);
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, e.getMessage());
        } finally {
            if (lock != null && lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    /**
     * Callable lock.
     *
     * @param lockKey
     * @param callable
     * @param seconds
     * @param <V>
     * @return
     * @throws Exception
     */
    public static <V> V lock(String lockKey, Callable<V> callable, int seconds) throws Exception {
        if (StringUtils.isEmpty(lockKey)) {
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, "分布式锁键值不能为空!");
        }
        return lock(lockKey, callable, seconds, MAX_HOLD_LOCK_SECONDS, 1);
    }

    /**
     * Callable lock tryTimes.
     *
     * @param lockKey
     * @param callable
     * @param seconds
     * @param tryTimes
     * @param <V>
     * @return
     * @throws Exception
     */
    public static <V> V lock(String lockKey, Callable<V> callable, int seconds, int tryTimes) throws Exception {
        if (StringUtils.isEmpty(lockKey)) {
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, "分布式锁键值不能为空");
        }
        return lock(lockKey, callable, seconds, MAX_HOLD_LOCK_SECONDS, tryTimes);
    }

    /**
     * Callable call.
     *
     * @param lockKey
     * @param callable
     * @param waitTime
     * @param leaseTime
     * @param tryTimes
     * @param <V>
     * @return
     * @throws Exception
     */
    private static <V> V lock(String lockKey, Callable<V> callable, long waitTime, long leaseTime, int tryTimes) throws Exception {
        RLock lock = SpringHelper.getBean(RedissonClient.class).getLock(lockKey);
        try {
            V result = null;
            if (getRedisLock(lock, waitTime, leaseTime, tryTimes)) {
                result = callable.call();
            }
            return result;
        } catch (InterruptedException e) {
            LOGGER.error("分布式锁 键值：{} 排队超时。超时时间:{} 秒", lockKey, waitTime);
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, e.getMessage());
        } finally {
            if (lock != null && lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    /**
     * 获取锁
     *
     * @param lock
     * @param waitTime
     * @param leaseTime
     * @param tryTimes
     * @return
     */
    private static boolean getRedisLock(RLock lock, long waitTime, long leaseTime, int tryTimes) {
        tryTimes = Math.min(MAX_GET_LOCK_TIMES, tryTimes);
        tryTimes = Math.max(1, tryTimes);
        while (tryTimes > 0) {
            try {
                if (lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS)) {
                    return true;
                }
                Thread.sleep(100);
            } catch (Exception e) {
                LOGGER.error("获取分布式锁失败!", e);
            }
            tryTimes--;
        }
        return false;
    }

}
