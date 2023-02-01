package com.harvest.basic.client.engine;

import com.harvest.core.annotation.Platform;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.feign.annotation.HarvestService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Map;

/**
 * @Author: Alodi
 * @Date: 2023/1/31 9:26 AM
 * @Description: 服务商平台驱动引擎
 **/
public abstract class PlatformServiceEngine implements ApplicationContextAware {

    private Map<String, Object> serviceMap;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        serviceMap = applicationContext.getBeansWithAnnotation(Platform.class);
    }

    public Object getService(int platformDefinitionType, int platformType) {
        for (Object service : serviceMap.values()) {
            Platform annotation = AnnotationUtils.findAnnotation(service.getClass(), Platform.class);
            if (annotation != null && annotation.type() == platformType && annotation.definition() == platformDefinitionType) {
                HarvestService localService = AnnotationUtils.findAnnotation(service.getClass(), HarvestService.class);
                if (localService != null) {
                    return service;
                }
            }
        }

        throw new StandardRuntimeException(ExceptionCodes.SYSTEM_EXCEPTION,
                "platformDefinitionType=" + platformDefinitionType +
                        "platformType=" + platformType +
                        "平台未定义，请检查平台jar 依赖是否添加!"
        );

    }

    protected abstract Object call(int platformType);

}
