package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.client.order.OrderWriteRepositoryClient;
import com.harvest.oms.repository.entity.FarmlandOmsOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 10:24 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_WRITE)
public class OrderWriteClientImpl implements OrderWriteClient {

    @Autowired
    private OrderWriteRepositoryClient orderWriteRepositoryClient;

    @Override
    public void updateDeclare(Long companyId, OrderInfoDO order) {
        FarmlandOmsOrderEntity entity = new FarmlandOmsOrderEntity();
        entity.setId(order.getOrderId());
        entity.setDeliveryNo(order.getDeliveryNo());
        entity.setWaitDeclare(true);
        orderWriteRepositoryClient.updateDeclare(companyId, entity);
    }
}