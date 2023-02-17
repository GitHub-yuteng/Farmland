package com.harvest.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 10:40 PM
 * @Description: 公用重复提交校验 公司-用户级别
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {

    int seconds() default 3;

    String remind() default "";

}
