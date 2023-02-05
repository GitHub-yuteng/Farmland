package com.harvest.oms.web.controller.order;

import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.domain.ResponseResult;
import com.harvest.core.path.HarvestOmsPath;
import com.harvest.oms.client.order.OrderDeliveryClient;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import com.harvest.oms.vo.order.declare.OrderDeclarationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

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

    @ApiOperation("订单申报信息")
    @PostMapping(value = "/declaration/get")
    public ResponseResult<Collection<OrderDeclarationVO>> listDeclaration(@RequestBody List<Long> orderIds) {
        orderDeliveryClient.listDeclaration(8510380986999420205L, orderIds);
        return null;
    }

    @ApiOperation("订单交运申报")
    @PostMapping(value = "/declare")
    public ResponseResult<BatchExecuteResult<String>> declare(@RequestBody Collection<SubmitDeclarationRequest> requests) {
        BatchExecuteResult<String> result = orderDeliveryClient.declare(8510380986999420205L, requests);
        return ResponseResult.success(result);
    }

}
