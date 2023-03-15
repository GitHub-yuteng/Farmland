package com.harvest.core.monitor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.harvest.core.annotation.Monitor;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.context.ContextHolder;
import com.harvest.core.context.SpringHelper;
import com.harvest.core.monitor.domain.MonitorEventMessage;
import com.harvest.core.monitor.enums.MonitorLevelEnum;
import com.harvest.core.monitor.enums.MonitorTypeEnum;
import com.harvest.core.monitor.notify.MonitorNotifyProcessor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/29 4:56 PM
 * @Description: TODO
 **/
@Aspect
@Component
public class HarvestExceptionMonitor implements GlobalMacroDefinition {

    private static final Logger LOGGER = LoggerFactory.getLogger(HarvestExceptionMonitor.class);

    /**
     * 监控通知线程池
     */
    private static final ExecutorService MONITOR_EVENT_NOTIFY_EXECUTOR = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder()
                    .setNameFormat("harvest-monitor-%d")
                    .setUncaughtExceptionHandler((thread, e) -> LOGGER.error("MONITOR_EVENT_NOTIFY_EXECUTOR:{} 发生异常", thread, e))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Around(value = "@annotation(com.harvest.core.annotation.Monitor)")
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
            long cost = stopWatch.getTotalTimeMillis();
            if (monitor.efficiencyWatch() > DEFAULT_0 && cost >= monitor.efficiencyWatch()) {
                this.notifyMonitorEvent(monitor, this.buildEfficiencyMessage(monitor, cost));
            }
        } catch (Throwable e) {
            throwable = e;
        }
        /*原样抛出异常信息*/
        if (throwable != null) {
            throw throwable;
        }
        return o;
    }

    private void notifyMonitorEvent(Monitor monitor, MonitorEventMessage message) {
        try {
            Future<?> submit = MONITOR_EVENT_NOTIFY_EXECUTOR.submit(() -> Arrays.stream(monitor.processors()).forEach(processor -> {
                MonitorNotifyProcessor bean = SpringHelper.getBean(processor);
                if (Objects.isNull(bean)) {
                    return;
                }
                bean.notifyEvent(message);
            }));
            submit.get(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.error("HarvestExceptionMonitor#notifyMonitorEvent#通知失败！", e);
        }
    }

    /**
     * 构建效率信息
     *
     * @param monitor
     * @param cost
     * @return
     */
    private MonitorEventMessage buildEfficiencyMessage(Monitor monitor, long cost) {
        MonitorEventMessage message = new MonitorEventMessage(ContextHolder.getContext());
        message.setMonitor(monitor.event().name());
        message.setEvent(monitor.event());
        message.setLevel(MonitorLevelEnum.NORMAL);
        message.setType(MonitorTypeEnum.EFFICIENCY);
        message.setExecuteCost(cost);
        message.setAtMembers(Arrays.stream(monitor.atMember()).collect(Collectors.toSet()));
        return message;
    }

}
