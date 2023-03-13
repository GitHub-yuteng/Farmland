package com.harvest.core.annotation;

import java.lang.annotation.*;

/**
 * @Author: Alodi
 * @Date: 2023/3/13 2:34 PM
 * @Description: 业务日志注解
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BizLog {
}
