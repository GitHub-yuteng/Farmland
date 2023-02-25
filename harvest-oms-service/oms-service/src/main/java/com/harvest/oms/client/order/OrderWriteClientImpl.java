package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.context.SpringHelper;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.client.order.OrderWriteRepositoryClient;
import com.harvest.oms.service.order.handler.save.OrderSaveHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 10:24 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_WRITE)
public class OrderWriteClientImpl implements OrderWriteClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderWriteClientImpl.class);


    @Autowired
    private OrderWriteRepositoryClient orderWriteRepositoryClient;

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
}
