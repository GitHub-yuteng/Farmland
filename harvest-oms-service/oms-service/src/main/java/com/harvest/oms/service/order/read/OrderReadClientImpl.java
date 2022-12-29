package com.harvest.oms.service.order.read;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.oms.repository.domain.order.OrderInfoDO;
import com.harvest.oms.service.constants.HarvestOmsApplications;
import com.harvest.oms.service.order.OrderReadClient;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 10:24 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_READ)
public class OrderReadClientImpl implements OrderReadClient {

    @Override
    public OrderInfoDO get(Long companyId, Long orderId) {
        System.out.println("test" + orderId);
        return new OrderInfoDO();
    }
}
