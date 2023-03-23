package com.harvest.core.service.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.RateLimitRuntimeException;
import com.harvest.core.service.annotation.RateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Alodi
 * @Date: 2023/3/21 11:21 AM
 * @Description: 限流切面
 **/
@Aspect
@Component
public class RateLimitAspect {

    private static final Map<String, RateLimiter> RATE_LIMITER_MAP = new ConcurrentHashMap<>(8);

    @Around(value = "@annotation(com.harvest.core.service.annotation.RateLimit)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        String clazzName = joinPoint.getTarget().getClass().getName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (!method.isAnnotationPresent(RateLimit.class)) {
            return joinPoint.proceed();
        }

        RateLimit rateLimit = method.getAnnotation(RateLimit.class);

        String realKey = clazzName + "#" + method.getName();
        final RateLimiter rateLimiter;
        if (RATE_LIMITER_MAP.containsKey(realKey)) {
            rateLimiter = RATE_LIMITER_MAP.get(realKey);
        } else {
            synchronized (realKey.intern()) {
                if (!RATE_LIMITER_MAP.containsKey(realKey)) {
                    RATE_LIMITER_MAP.put(realKey, RateLimiter.create(rateLimit.permits()));
                }
                rateLimiter = RATE_LIMITER_MAP.get(realKey);
            }
        }

        if (!rateLimiter.tryAcquire(1, 500, TimeUnit.MILLISECONDS)) {
            throw new RateLimitRuntimeException(ExceptionCodes.RATE_LIMIT_ERROR, "前方拥挤，亲请稍后再试～");
        }

        return joinPoint.proceed();
    }
}
