package com.harvest.oms.service.order;

import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.batch.BatchId;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.service.lock.DistributedLockUtils;
import com.harvest.core.utils.ActuatorUtils;
import com.harvest.oms.client.order.OrderReadClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.redis.OmsKeyPrefix;
import com.harvest.oms.redis.flow.OrderAuditFlowKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/9 2:34 PM
 * @Description: TODO
 **/
public abstract class AbstractBizOrderService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractBizOrderService.class);

    @Autowired
    protected OrderReadClient orderReadClient;

    /**
     * 订单加锁异步批量处理器
     *
     * @param companyId 公司ID
     * @param orderIds  订单Id集合
     * @param consumer  处理器
     * @return 处理结果
     */
    protected BatchExecuteResult<String> SyncUniqueOrderParallelFailAllowBatchExecute(Long companyId, Collection<Long> orderIds, Consumer<OrderInfoDO> consumer) {
        Map<Long, String> orderMap = new ConcurrentHashMap<>(2);
        return ActuatorUtils.parallelFailAllowBatchExecute(orderIds.stream().map(BatchId::build).collect(Collectors.toList()),
                batchId -> {
                    try {
                        DistributedLockUtils.lock(OmsKeyPrefix.ORDER_UNIQUE_KEY, batchId.getLockKey(),
                                () -> {
                                    OrderInfoDO order = orderReadClient.getOrderRich(companyId, batchId.getId());
                                    orderMap.put(batchId.getId(), order.getOrderNo());
                                    consumer.accept(order);
                                }
                                , OrderAuditFlowKey.ORDER_AUDIT_KEY.expireSeconds());
                    } catch (Exception e) {
                        LOGGER.error("订单处理失败, 订单Id: {}", batchId.getId(), e);
                        throw new StandardRuntimeException(ExceptionCodes.OMS_MODULE_ERROR, e.getMessage());
                    }
                },
                batchId -> orderMap.get(batchId.getId()));
    }

}
