package com.harvest.core.context;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/1/10 11:17 AM
 * @Description: TODO
 **/
@Component
public final class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static SpringApplication springApplication;

    /**
     * 获取 Spring context
     *
     * @return 上下文
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext context) throws BeansException {
        applicationContext = context;
    }
}
