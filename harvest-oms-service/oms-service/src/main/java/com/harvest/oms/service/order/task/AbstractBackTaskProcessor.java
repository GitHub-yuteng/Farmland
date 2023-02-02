package com.harvest.oms.service.order.task;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.harvest.core.service.lock.DistributedLockUtils;
import com.harvest.oms.service.redis.OrderBackStatTaskKey;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @Author: Alodi
 * @Date: 2023/2/1 8:37 PM
 * @Description: 抽象后台任务处理器
 **/
public abstract class AbstractBackTaskProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractBackTaskProcessor.class);

    /**
     * 最小执行间隔时间 默认10s
     */
    protected final int minInterval;

    /**
     * 执行线程池
     */
    protected final ExecutorService backStatExecutor;

    /**
     * 任务构建
     *
     * @param taskName
     * @param minInterval
     * @param coreThread
     */
    public AbstractBackTaskProcessor(String taskName, int minInterval, int coreThread) {
        if (StringUtils.isEmpty(taskName)) {
            throw new IllegalArgumentException("请指定后台任务线程名称");
        }
        if (minInterval <= 0) {
            minInterval = 10;
        }
        coreThread = Math.max(1, coreThread);
        this.minInterval = minInterval;

        backStatExecutor = new ThreadPoolExecutor(coreThread, coreThread, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder()
                        .setNameFormat(taskName + "-back-stat-%d")
                        .setUncaughtExceptionHandler((thread, e) -> LOGGER.error(taskName + "-back-stat-%d:{} 发生异常", thread, e))
                        .build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void execute(Long companyId, OrderBackStatTaskKey orderBackStatTaskKey) {
        // 对公司级别上锁
        synchronized (String.valueOf(companyId).intern()) {
            try {
                Boolean lock = DistributedLockUtils.lock(orderBackStatTaskKey, () -> backStatExecutor.submit(this.getTask(companyId)).get());
            } catch (Exception e) {
                LOGGER.error(String.format("公司后台任务执行异常, companyId:%s, 异常原因：%s", companyId, e.getMessage()), e.getCause());
            } finally {
                System.out.println("释放锁！");
            }
        }
    }

    /**
     * 返回需要执行的任务
     *
     * @param companyId 公司id
     * @return 任务
     */
    protected abstract Callable<Boolean> getTask(long companyId);

}