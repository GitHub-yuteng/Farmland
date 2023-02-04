package com.harvest.oms.web.controller.order;

import com.harvest.core.path.HarvestOmsPath;
import com.harvest.oms.client.order.OrderDeliveryClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:51 PM
 * @Description: 订单发货服务
 **/
@Api(value = "订单业务服务", tags = "订单业务服务")
@RestController
@RequestMapping(value = HarvestOmsPath.OrderPath.ORDER_DELIVERY_PATH)
public class OrderDeliveryController {

    @Autowired
    private OrderDeliveryClient orderDeliveryClient;

    @ApiOperation("订单交运申报")
    @PostMapping(value = "/declare")
    public void declare() {
        orderDeliveryClient.declare(8510380986999420205L);
    }

}
