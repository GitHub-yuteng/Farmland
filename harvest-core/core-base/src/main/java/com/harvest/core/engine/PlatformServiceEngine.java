package com.harvest.core.engine;

import com.harvest.core.annotation.Platform;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.enums.platform.PlatformDefinitionEnum;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Map;
import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2023/1/31 9:26 AM
 * @Description: 服务商平台驱动引擎
 **/
public abstract class PlatformServiceEngine implements ApplicationContextAware {

    private Map<String, Object> services;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        services = applicationContext.getBeansWithAnnotation(Platform.class);
    }

    /**
     * 驱动引擎 获取平台服务
     *
     * @param platformDefinitionEnum
     * @param platformType
     * @return
     */
    public Object getService(PlatformDefinitionEnum platformDefinitionEnum, int platformType) {
        for (Object service : services.values()) {
            Platform annotation = AnnotationUtils.findAnnotation(service.getClass(), Platform.class);
            if (Objects.nonNull(annotation) && annotation.definition() == platformDefinitionEnum && annotation.type() == platformType) {
                HarvestService localService = AnnotationUtils.findAnnotation(service.getClass(), HarvestService.class);
                // 本地服务
                if (Objects.nonNull(localService)) {
                    return localService;
                }
                return service;
            }
        }

        throw new StandardRuntimeException(ExceptionCodes.SYSTEM_EXCEPTION,
                "platformDefinitionEnum=" + platformDefinitionEnum +
                        "platformType=" + platformType +
                        "平台未定义，请检查平台jar 依赖是否添加!"
        );

    }

    protected abstract Object call(int platformType);

}
