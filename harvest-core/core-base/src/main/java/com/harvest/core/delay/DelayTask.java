package com.harvest.core.delay;

import com.harvest.core.domain.CompanyId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @Author: Alodi
 * @Date: 2023/2/10 2:48 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class DelayTask extends CompanyId implements Delayed {

    private static final long serialVersionUID = 2695586265847769402L;

    /**
     * 任务描述 用户日志记录
     */
    private String description;

    /**
     * 开始时间，默认为任务创建时间
     */
    private long start = System.currentTimeMillis();

    /**
     * 延迟时长 秒级
     */
    private int delay = 5;

    /**
     * 步长，单位毫秒。如果间隔5s，步长5s，则任务开始后第一次执行5s后，第二次执行为15s后
     */
    private int step;

    /**
     * 失败重试总次数 默认不重试
     */
    private int retryIfFailed;

    /**
     * 已重试次数
     */
    private final AtomicInteger retryTimes = new AtomicInteger();

    /**
     * 额外信息实体
     */
    private Object extension;

    /**
     * 执行的任务
     */
    private Consumer<DelayTask> consumer;

    /**
     * 延迟任务执行失败并且不会再继续重试后执行
     */
    private Consumer<DelayTask> onFailed;

    /**
     * 延迟任务执行成功后执行
     */
    private Consumer<DelayTask> onSuccess;

    public DelayTask(Long companyId, String description, int step, int retryIfFailed, Consumer<DelayTask> consumer) {
        super(companyId);
        this.description = description;
        this.step = step;
        this.retryIfFailed = retryIfFailed;
        this.consumer = consumer;
    }

    public DelayTask(Long companyId, String description, int step, int retryIfFailed, Object extension, Consumer<DelayTask> consumer) {
        super(companyId);
        this.description = description;
        this.step = step;
        this.retryIfFailed = retryIfFailed;
        this.extension = extension;
        this.consumer = consumer;
    }

    public DelayTask(Long companyId, String description, int delay, int step, int retryIfFailed, Consumer<DelayTask> consumer) {
        super(companyId);
        this.description = description;
        this.delay = delay;
        this.step = step;
        this.retryIfFailed = retryIfFailed;
        this.consumer = consumer;
    }

    public DelayTask(Long companyId, String description, int delay, int step, int retryIfFailed, Object extension, Consumer<DelayTask> consumer) {
        super(companyId);
        this.description = description;
        this.delay = delay;
        this.step = step;
        this.retryIfFailed = retryIfFailed;
        this.extension = extension;
        this.consumer = consumer;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((start + (delay * 1000)) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }
}
