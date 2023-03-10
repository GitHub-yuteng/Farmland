package com.harvest.oms.web.controller.order;

import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.context.ContextHolder;
import com.harvest.core.domain.ResponseResult;
import com.harvest.core.path.HarvestOmsPath;
import com.harvest.oms.client.order.OrderDeliveryClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:51 PM
 * @Description: 订单发货服务
 **/
@Api(value = "订单发货服务", tags = "订单发货服务")
@RestController
@RequestMapping(value = HarvestOmsPath.OrderPath.ORDER_DELIVERY_PATH)
public class OrderDeliveryController {

    @Autowired
    private OrderDeliveryClient orderDeliveryClient;

    @ApiOperation("订单发货")
    @PostMapping(value = "/batch/submit")
    public ResponseResult<BatchExecuteResult<String>> delivery(@RequestBody List<Long> orderIds) {
        Long companyId = ContextHolder.getContext().getCompanyId();
        BatchExecuteResult<String> result = orderDeliveryClient.delivery(companyId, orderIds);
        return ResponseResult.success(result);
    }

}
