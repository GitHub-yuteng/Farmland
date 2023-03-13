package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.context.SpringHelper;
import com.harvest.core.service.redis.CacheService;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.service.order.AbstractBizOrderService;
import com.harvest.oms.service.order.handler.delivery.OrderDeliveryExecutor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/3/10 3:42 PM
 * @Description: 订单发货
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_DELIVERY)
public class OrderDeliveryClientImpl extends AbstractBizOrderService implements OrderDeliveryClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderDeliveryClientImpl.class);

    @Autowired
    private CacheService cacheService;

    @Override
    public BatchExecuteResult<String> delivery(Long companyId, Collection<Long> orderIds) {
        if (CollectionUtils.isEmpty(orderIds)) {
            return BatchExecuteResult.empty();
        }
        return super.SyncUniqueOrderParallelFailAllowBatchExecute(companyId, orderIds, order -> {
            SpringHelper.getBean(OrderDeliveryExecutor.class).exec(companyId, order);
        });
    }
}
