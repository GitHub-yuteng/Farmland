package com.harvest.core.service.aspect;

import com.harvest.core.annotation.RepeatSubmit;
import com.harvest.core.context.ContextHolder;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.service.redis.CacheService;
import com.harvest.core.service.redis.repeat.RepeatKeyPrefix;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 10:52 PM
 * @Description: TODO
 **/
@Aspect
@Component
public class RepeatSubmitAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepeatSubmitAspect.class);

    @Autowired
    private CacheService cacheService;

    @Around(value = "@annotation(com.harvest.core.annotation.RepeatSubmit)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RepeatSubmit repeatSubmit = method.getAnnotation(RepeatSubmit.class);
        int seconds = repeatSubmit.seconds();
        String methodName = method.getName();
        String realKey = methodName + "-" + ContextHolder.getContext().getCompanyId() + "-" + ContextHolder.getContext().getUserId();
        boolean exists = cacheService.exists(RepeatKeyPrefix.INTERVAL_REPEAT, realKey);
        if (exists) {
            throw new StandardRuntimeException(ExceptionCodes.BASE_MODULE_ERROR, StringUtils.isNotEmpty(repeatSubmit.remind()) ? repeatSubmit.remind() : "请稍后重试～");
        }
        cacheService.set(RepeatKeyPrefix.INTERVAL_REPEAT, realKey, System.currentTimeMillis(), seconds);
        return joinPoint.proceed();
    }

}
