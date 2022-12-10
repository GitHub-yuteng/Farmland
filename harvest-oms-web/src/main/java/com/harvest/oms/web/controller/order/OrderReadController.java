package com.harvest.oms.web.controller.order;

import com.harvest.oms.service.order.OrderReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:51 PM
 * @Description: 订单读服务
 **/
@RestController
@RequestMapping(value = "/order")
public class OrderReadController {

    @Autowired
    private OrderReadService orderReadService;

    @PostMapping(value = "/get")
    public String getOrderInfo(@RequestParam("orderId") long orderId) {
        return orderReadService.getOrderInfo(orderId);
    }

}
