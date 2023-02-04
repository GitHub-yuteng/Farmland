package com.harvest.core.annotation.feign;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Primary
@RestController
@RequestMapping
public @interface HarvestService {

    /**
     * Alias for {@link RestController#value}.
     */
    @AliasFor(annotation = RestController.class)
    String value() default "";

    /**
     * Alias for {@link RequestMapping#path}.
     */
    @AliasFor(annotation = RequestMapping.class)
    String path() default "";
}