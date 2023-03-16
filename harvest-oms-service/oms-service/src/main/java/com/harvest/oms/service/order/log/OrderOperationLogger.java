package com.harvest.oms.service.order.log;

import com.harvest.core.log.OperationLog;
import com.harvest.core.service.biz.BizOperationLog;
import com.harvest.oms.repository.client.order.OrderOperationLogRepositoryClient;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/3/13 3:16 PM
 * @Description: TODO
 **/
@Component
public class OrderOperationLogger implements BizOperationLog<OrderOperationLog> {

    @Autowired
    private OrderOperationLogRepositoryClient orderOperationLogRepositoryClient;

    /**
     * 匹配
     */
    @Override
    public Class<OrderOperationLog> match() {
        return OrderOperationLog.class;
    }

    /**
     * 保存
     *
     * @param log
     */
    @Override
    public void store(OperationLog log) {
        this.batchStore(Collections.singleton(log));
    }

    /**
     * 批量保存
     *
     * @param logs
     */
    @Override
    public void batchStore(Collection<OperationLog> logs) {
        if (CollectionUtils.isEmpty(logs)) {
            return;
        }
        orderOperationLogRepositoryClient.batchStore(logs.stream().map(log -> (OrderOperationLog) log).collect(Collectors.toList()));
    }
}
