package com.harvest.oms.service.order;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 10:24 PM
 * @Description: TODO
 **/
@Service
@RestController
public class OrderReadServiceImpl implements OrderReadService {

    @Override
    public String getOrderInfo(long orderId) {
        return orderId + ": 信息";
    }
}
