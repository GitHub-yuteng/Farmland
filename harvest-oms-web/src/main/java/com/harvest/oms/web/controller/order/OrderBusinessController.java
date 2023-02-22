package com.harvest.oms.web.controller.order;

import com.harvest.core.path.HarvestOmsPath;
import com.harvest.oms.client.order.OrderMatchClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:51 PM
 * @Description: 订单业务服务
 **/
@Api(value = "订单业务服务", tags = "订单业务服务")
@RestController
@RequestMapping(value = HarvestOmsPath.OrderPath.ORDER_BUSINESS_PATH)
public class OrderBusinessController {

    @Autowired
    private OrderMatchClient orderMatchClient;


}
