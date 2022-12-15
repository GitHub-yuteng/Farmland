package com.harvest.oms.service.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.oms.repository.domain.order.OrderInfoDO;
import com.harvest.oms.repository.service.order.OrderReadRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 10:24 PM
 * @Description: TODO
 **/
@HarvestService
public class OrderReadServiceImpl implements OrderReadService {

    @Autowired
    private OrderReadRepositoryService orderReadRepositoryService;

    @Override
    public OrderInfoDO get(Long orderId, Long id) {
        return orderReadRepositoryService.getOrderInfo(orderId);
    }
}
