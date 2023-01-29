package com.harvest.core.monitor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.harvest.core.monitor.anno.Monitor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Alodi
 * @Date: 2023/1/29 4:56 PM
 * @Description: TODO
 **/
@Aspect
@Component
public class HarvestExceptionMonitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(HarvestExceptionMonitor.class);

    private final static String MONITOR_POINT = "@annotation(com.harvest.core.monitor.anno.Monitor)";

    /**
     * 监控通知线程池
     */
    private final static Executor MONITOR_EVENT_NOTIFY_EXECUTOR = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder()
                    .setNameFormat("harvest-monitor-%d")
                    .setUncaughtExceptionHandler((thread, e) -> LOGGER.error("MONITOR_EVENT_NOTIFY_EXECUTOR:{} 发生异常", thread, e))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Around(MONITOR_POINT)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Monitor monitor = method.getAnnotation(Monitor.class);
        Object o = null;
        Throwable throwable = null;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            o = joinPoint.proceed(joinPoint.getArgs());
            stopWatch.stop();
            long totalTimeMillis = stopWatch.getTotalTimeMillis();
        } catch (Throwable e) {
            throwable = e;
        }
        /*原样抛出异常信息*/
        if (throwable != null) {
            throw throwable;
        }
        return o;
    }

}
