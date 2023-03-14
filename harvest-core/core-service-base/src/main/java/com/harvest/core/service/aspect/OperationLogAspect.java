package com.harvest.core.service.aspect;

import com.google.common.collect.Maps;
import com.harvest.core.log.OperationLog;
import com.harvest.core.service.biz.BizOperationLog;
import com.harvest.core.service.utils.BizLogUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2023/3/13 2:40 PM
 * @Description: TODO
 **/
@Aspect
@Component
public class OperationLogAspect implements InitializingBean {

    @Autowired(required = false)
    private List<BizOperationLog<? extends OperationLog>> bizOperationLogs;

    private static final Map<Class<? extends OperationLog>, BizOperationLog<? extends OperationLog>> LOG_MAP = Maps.newHashMap();

    @Override
    public void afterPropertiesSet() {
        if (CollectionUtils.isEmpty(bizOperationLogs)) {
            return;
        }

        bizOperationLogs.stream().filter(Objects::nonNull).filter(biz -> Objects.nonNull(biz.match()))
                .forEach(log -> LOG_MAP.put(log.match(), log));
    }

    @After(value = "@annotation(com.harvest.core.annotation.BizLog)")
    public void after(JoinPoint joinPoint) {
        try {
            // 在这里已经线程变了
            System.out.println("change: " + Thread.currentThread().getName());
            List<OperationLog> operationLogs = BizLogUtils.get();
            if (CollectionUtils.isEmpty(operationLogs)) {
                return;
            }
            operationLogs.forEach(this::store);
        } finally {
            BizLogUtils.clear();
        }
    }

    public void store(OperationLog log) {
        BizOperationLog<? extends OperationLog> bizOperationLog = LOG_MAP.get(log.getClass());
        if (Objects.isNull(bizOperationLog)) {
            return;
        }
        bizOperationLog.store(log);
    }
}
