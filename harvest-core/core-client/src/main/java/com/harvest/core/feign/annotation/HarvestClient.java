package com.harvest.core.feign.annotation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@FeignClient(primary = false)
public @interface HarvestClient {

    /**
     * Alias for {@link FeignClient#fallback}
     */
    @AliasFor(annotation = FeignClient.class)
    Class<?> fallback() default void.class;

    /**
     * Alias for {@link FeignClient#fallbackFactory}
     */
    @AliasFor(annotation = FeignClient.class)
    Class<?> fallbackFactory() default void.class;

    /**
     * Alias for {@link FeignClient#value}
     */
    @AliasFor(annotation = FeignClient.class, attribute = "value")
    String value() default "";

    /**
     * Alias for {@link FeignClient#name}
     */
    @AliasFor(annotation = FeignClient.class, attribute = "name")
    String name() default "";

    /**
     * Alias for {@link FeignClient#path}
     */
    @AliasFor(annotation = FeignClient.class)
    String path() default "";

    /**
     * Alias for {@link FeignClient#url}
     */
    @AliasFor(annotation = FeignClient.class)
    String url() default "";
}