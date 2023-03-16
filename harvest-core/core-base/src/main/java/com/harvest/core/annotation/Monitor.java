package com.harvest.core.annotation;

import com.harvest.core.monitor.enums.MemberContactEnum;
import com.harvest.core.monitor.enums.MonitorEventEnum;
import com.harvest.core.monitor.notify.MonitorNotifyProcessor;
import com.harvest.core.monitor.notify.ding.DingTalkNotifyProcessor;
import com.harvest.core.monitor.notify.feishu.FlyBookNotifyProcessor;
import com.harvest.core.monitor.notify.log.LogNotifyProcessor;

import java.lang.annotation.*;

/**
 * @Author: Alodi
 * @Date: 2023/1/29 4:38 PM
 * @Description: 异常监控通知注解
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Monitor {

    /**
     * 监控名称
     */
    MonitorEventEnum event();

    /**
     * 效率监控 单位：毫秒 如果没有配置或者配置的是1000 则不告警
     */
    int efficiencyWatch() default 0;

    /**
     * 忽略异常
     */
    Class<? extends Exception>[] ignoreException() default {};

    /**
     * 通知处理器
     */
    Class<? extends MonitorNotifyProcessor>[] processors() default {
            LogNotifyProcessor.class,
            DingTalkNotifyProcessor.class,
            FlyBookNotifyProcessor.class
    };

    /**
     * 需要@的人，需要成员在群里才能正常At
     */
    MemberContactEnum[] atMember() default {};
}
