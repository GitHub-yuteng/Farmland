package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.context.SpringHelper;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.client.order.OrderWriteRepositoryClient;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.biz.AbstractBizOrderService;
import com.harvest.oms.service.order.handler.save.OrderSaveHandler;
import com.harvest.oms.service.order.handler.update.OrderUpdateHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 10:24 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_WRITE)
public class OrderWriteClientImpl extends AbstractBizOrderService implements OrderWriteClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderWriteClientImpl.class);

    @Autowired
    private OrderWriteRepositoryClient orderWriteRepositoryClient;

    @Autowired(required = false)
    private List<OrderUpdateHandler> orderUpdateHandlers;

    @Override
    public void build(Long companyId) {
        OrderInfoDO order = new OrderInfoDO();
        this.saveOrder(companyId, order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(Long companyId, OrderInfoDO order) {
        SpringHelper.getBean(OrderSaveHandler.class).save(companyId, order);
    }

    @Override
    public void updateDeclare(Long companyId, OrderInfoDO order) {
        orderWriteRepositoryClient.updateDeclare(companyId, order);
    }

    @Override
    public void updateOrderStatus(Long companyId, OrderInfoDO order) {
        orderWriteRepositoryClient.updateOrderStatus(companyId, order.getOrderId(), order.getOrderStatus());
    }

    @Override
    public BatchExecuteResult<String> batchUpdate(Long companyId, OrderSubmitUpdateField field) {
        List<Long> orderIds = field.getOrderIds();
        if (CollectionUtils.isEmpty(orderIds) || CollectionUtils.isEmpty(orderUpdateHandlers)) {
            return BatchExecuteResult.empty();
        }
        return super.SyncUniqueOrderParallelFailAllowBatchExecute(companyId, orderIds,
                order -> orderUpdateHandlers.forEach(handler -> {
                    if (handler.match(companyId, field.getUpdateType())) {
                        handler.handle(companyId, field, order);
                    }
                }));
    }
}
