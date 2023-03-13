package com.harvest.core.delay;


import jodd.util.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author: Alodi
 * @Date: 2023/2/10 3:03 PM
 * @Description: 延迟任务执行器
 **/
public class DelayTaskExecutor {

    /**
     * 延迟任务执行器
     */
    private static volatile DelayTaskExecutor EXECUTOR;

    /**
     * 单例延迟执行器
     *
     * @return
     */
    public static DelayTaskExecutor getInstance() {
        if (EXECUTOR == null) {
            synchronized (DelayTaskExecutor.class) {
                if (EXECUTOR == null) {
                    EXECUTOR = new DelayTaskExecutor();
                }
            }
        }
        return EXECUTOR;
    }

    /**
     * 延迟队列
     */
    private static final DelayQueue<DelayTask> DELAY_TASK_QUEUE = new DelayQueue<>();

    /**
     * 延迟任务执行线程池
     */
    private static ThreadPoolExecutor delayTaskExecutor;

    /**
     * 延迟任务执行器初始化
     */
    private DelayTaskExecutor() {
        /*任务循环线程池*/
        ExecutorService loopExecutor = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().withNameFormat("harvest-delay-task-loop-executor").get()
        );

        loopExecutor.submit(() -> {
            while (true) {
                DelayTask t;
                try {
                    t = DELAY_TASK_QUEUE.take();
                } catch (Exception e) {
                    continue;
                }
                this.executeTask(t);
            }
        });
        /*最多50个线程执行延迟任务*/
        delayTaskExecutor = new ThreadPoolExecutor(50, 50, 2000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().withNameFormat("harvest-delay-task-%d").get()
        );
        delayTaskExecutor.allowCoreThreadTimeOut(true);

    }

    private void executeTask(DelayTask task) {
        if (task == null || task.getCompanyId() == 0L) {
            return;
        }
        try {
            delayTaskExecutor.execute(() -> {
                try {
                    task.getRetryTimes().incrementAndGet();
                    task.getConsumer().accept(task);
                    if (task.getOnSuccess() != null) {
                        task.getOnSuccess().accept(task);
                    }
                } catch (Exception e) {
                    this.postTaskExecuteFailedProcess(task);
                }
            });
        } catch (Exception e) {
            /*如果未达到重试次数则继续创建任务，放回队列中*/
            this.postTaskExecuteFailedProcess(task);
        }
    }

    private void postTaskExecuteFailedProcess(DelayTask task) {
        int retryTimes = task.getRetryTimes().get();
        if (retryTimes < task.getRetryIfFailed()) {
            long time = (long) task.getStep() * 1000;
            task.setStart(System.currentTimeMillis() + time * task.getRetryTimes().get());
            this.pushTask(task);
            return;
        }
        if (task.getOnFailed() != null) {
            task.getOnFailed().accept(task);
        }
    }

    public void pushTask(DelayTask task) {
        if (task == null || task.getCompanyId() == 0L) {
            return;
        }
        DELAY_TASK_QUEUE.add(task);
    }
}
