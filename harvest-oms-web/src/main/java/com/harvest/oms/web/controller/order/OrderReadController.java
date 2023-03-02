package com.harvest.oms.web.controller.order;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.context.Context;
import com.harvest.core.context.ContextHolder;
import com.harvest.core.domain.ResponseResult;
import com.harvest.core.path.HarvestOmsPath;
import com.harvest.oms.client.order.OrderReadClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.ContentHandler;


/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:51 PM
 * @Description: 订单读服务
 **/
@Api(value = "订单读服务", tags = "订单读服务")
@RestController
@RequestMapping(value = HarvestOmsPath.OrderPath.ORDER_READ_PATH)
public class OrderReadController implements GlobalMacroDefinition {

    @Autowired
    private OrderReadClient orderReadClient;

    @ApiOperation("订单查询")
    @PostMapping(value = "/get")
    public ResponseResult<OrderInfoDO> getOrderInfo(@RequestParam(OMS.ORDER_ID) Long orderId) {
        Context context = ContextHolder.getContext();
        return ResponseResult.success(orderReadClient.getOrderRich(context.getCompanyId(), orderId));
    }

}
