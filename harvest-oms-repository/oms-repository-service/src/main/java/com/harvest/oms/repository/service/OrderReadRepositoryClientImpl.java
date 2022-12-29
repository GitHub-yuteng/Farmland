package com.harvest.oms.repository.service;

import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.entity.FarmlandOmsOrderEntity;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderMapper;
import com.harvest.oms.repository.service.order.OrderReadRepositoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 8:03 PM
 * @Description: TODO
 **/
@RestController
public class OrderReadRepositoryClientImpl implements OrderReadRepositoryClient {

    @Autowired
    private FarmlandOmsOrderMapper farmlandOmsOrderMapper;

    @Override
    public OrderSimplePO getOrderInfo(Long orderId) {
        FarmlandOmsOrderEntity farmlandOmsOrderEntity = farmlandOmsOrderMapper.selectById(orderId);
        return null;
    }
}
