package com.harvest.core.context;

import org.springframework.context.ApplicationContext;

/**
 * @Author: Alodi
 * @Date: 2023/1/10 10:49 AM
 * @Description: TODO
 **/
public class SpringHelper {

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    public static ApplicationContext context() {
        return SpringContext.getApplicationContext();
    }

    /**
     * 获取目标 Bean 实例
     *
     * @param clazz 类型
     * @return Bean 实例
     */
    public static <T> T getBean(Class<T> clazz) {
        if (clazz == null) {
            return null;
        }
        ApplicationContext ac = context();
        return ac == null ? null : bean(ac, clazz);
    }

    /**
     * 获取上下文实例
     */
    protected static <T> T bean(ApplicationContext applicationContext, Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}
