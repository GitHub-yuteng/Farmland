package com.harvest.core.service.annotation;

import java.lang.annotation.*;

/**
 * @Author: Alodi
 * @Date: 2023/3/21 10:41 AM
 * @Description: TODO
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

    double permits() default Double.MAX_VALUE;

}
