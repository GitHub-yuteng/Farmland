package com.harvest.core.annotation;

import com.harvest.core.enums.platform.PlatformDefinitionEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @Author: Alodi
 * @Date: 2023/1/31 9:31 AM
 * @Description: 服务商平台定义
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Platform {

    @AliasFor("value")
    int type() default 0;

    @AliasFor("type")
    int value() default 0;

    PlatformDefinitionEnum definition();
}
