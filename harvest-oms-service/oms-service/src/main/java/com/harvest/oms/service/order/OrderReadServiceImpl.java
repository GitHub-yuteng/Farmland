package com.harvest.oms.service.order;

import com.harvest.oms.repository.domain.order.OrderInfoDO;
import com.harvest.oms.repository.service.order.OrderReadRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 10:24 PM
 * @Description: TODO
 **/
@Service
public class OrderReadServiceImpl implements OrderReadService {

    @Autowired
    private OrderReadRepositoryService orderReadRepositoryService;

    @Override
    public OrderInfoDO getOrderInfo() {
        return orderReadRepositoryService.getOrderInfo(1L);
    }

    @Override
    public String test() {
        return "test";
    }
}
