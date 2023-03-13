package com.harvest.core.delay.t;

import com.harvest.core.delay.DelayTask;
import com.harvest.core.delay.DelayTaskExecutor;
import com.harvest.core.utils.JsonUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Alodi
 * @Date: 2023/2/10 4:54 PM
 * @Description: TODO
 **/
public class TestDelayTask {

    private static final AtomicInteger COUNT = new AtomicInteger();

    /**
     * example: 步进 step 为 2s 失败重试5次 {@link com.harvest.core.delay.DelayTask#getDelay(java.util.concurrent.TimeUnit)}
     * <p>
     * delay:4935000000
     * delay:-7000000
     * hello world/1
     * delay:7000000000
     * delay:-4000000
     * hello world/2
     * delay:9000000000
     * delay:-6000000
     * hello world/3
     * delay:10999000000
     * delay:-4000000
     * hello world/4
     * delay:13000000000
     * delay:-1000000
     * hello world/5
     * {"companyId":123,"description":"hello world","start":1676020615120,"delay":5,"step":2,"retryIfFailed":5,"retryTimes":5,"consumer":{},"onFailed":{},"onSuccess":{}}
     * 执行最终失败
     *
     * @param args
     */
    public static void main(String[] args) {
        DelayTask delayTask = new DelayTask(123L, "hello world", 2, 5, TestDelayTask::sout);
        delayTask.setOnFailed(t -> System.out.println(JsonUtils.object2Json(t) + "执行最终失败"));
        delayTask.setOnSuccess(t -> System.out.println(JsonUtils.object2Json(t) + "执行最终成功"));
        DelayTaskExecutor.getInstance().pushTask(delayTask);
    }

    private static void sout(DelayTask t) {
        System.out.println(t.getDescription() + "/" + COUNT.incrementAndGet());
        throw new RuntimeException("test");
    }
}
