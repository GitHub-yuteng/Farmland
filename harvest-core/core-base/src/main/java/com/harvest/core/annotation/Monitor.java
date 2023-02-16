package com.harvest.core.annotation;

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
     * 效率监控 单位：毫秒 如果没有配置或者配置的是1000 则不告警
     */
    int efficiencyWatch() default 0;

    /**
     * 忽略异常
     */
    Class<? extends Exception>[] ignoreException() default {};

}
