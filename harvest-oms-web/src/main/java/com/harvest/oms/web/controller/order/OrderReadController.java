package com.harvest.oms.web.controller.order;

import com.harvest.core.path.HarvestOmsPath;
import com.harvest.oms.repository.domain.order.OrderInfoDO;
import com.harvest.oms.service.order.OrderReadClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:51 PM
 * @Description: 订单读服务
 **/
@Api(value = "订单读服务", tags = "订单读服务")
@RestController
@RequestMapping(value = HarvestOmsPath.OrderPath.OMS_READ_PATH)
public class OrderReadController {

    @Autowired
    private OrderReadClient orderReadClient;

    @ApiOperation("订单查询")
    @RequestMapping(value = "/get")
    public OrderInfoDO getOrderInfo(@RequestParam("orderId") Long orderId) {
        return orderReadClient.get(1L, orderId);
    }
}
