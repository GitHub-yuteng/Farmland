package com.harvest.oms.repository.client;

import com.harvest.core.feign.annotation.HarvestService;
import com.harvest.oms.repository.client.order.OrderReadRepositoryClient;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.mapper.order.read.OrderItemReadMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 8:03 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsRepositoryApplications.Path.ORDER_RICH)
public class OrderReadRepositoryClientImpl implements OrderReadRepositoryClient {

    @Autowired
    private OrderItemReadMapper orderItemReadMapper;

    @Override
    public OrderSimplePO get(Long companyId, Long orderId) {
        return null;
    }

    @Override
    public Collection<OrderItemSimplePO> listOrderItemByOrderIds(Long companyId, List<Long> orderIds) {
        return orderItemReadMapper.listOrderItemByOrderIds(companyId, orderIds);
    }
}
