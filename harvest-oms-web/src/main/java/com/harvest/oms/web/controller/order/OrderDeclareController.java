package com.harvest.oms.web.controller.order;

import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.context.ContextHolder;
import com.harvest.core.domain.ResponseResult;
import com.harvest.core.path.HarvestOmsPath;
import com.harvest.oms.client.order.OrderDeclareClient;
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
 * @Description: 订单申报服务
 **/
@Api(value = "订单申报服务", tags = "订单申报服务")
@RestController
@RequestMapping(value = HarvestOmsPath.OrderPath.ORDER_DECLARE_PATH)
public class OrderDeclareController {

    @Autowired
    private OrderDeclareClient orderDeclareClient;

    @ApiOperation("订单申报信息")
    @PostMapping(value = "/get")
    public ResponseResult<Collection<OrderDeclarationVO>> listDeclaration(@RequestBody List<Long> orderIds) {
        Long companyId = ContextHolder.getContext().getCompanyId();
//        Collection<OrderDeclarationVO> result = orderDeclareClient.listDeclaration(companyId, orderIds);
        return ResponseResult.success(null);
    }

    @ApiOperation("订单交运申报")
    @PostMapping(value = "/batch/submit")
    public ResponseResult<BatchExecuteResult<String>> declare(@RequestBody Collection<SubmitDeclarationRequest> requests) {
        Long companyId = ContextHolder.getContext().getCompanyId();
        BatchExecuteResult<String> result = orderDeclareClient.declare(companyId, requests);
        return ResponseResult.success(result);
    }

    @ApiOperation("刷新申报信息")
    @PostMapping(value = "/refresh")
    public ResponseResult<String> refreshDeclaration(@RequestBody List<Long> orderIds) {
        Long companyId = ContextHolder.getContext().getCompanyId();
        orderDeclareClient.refreshDeclaration(companyId, orderIds);
        return ResponseResult.success();
    }

    @ApiOperation("重新获取面单信息")
    @PostMapping(value = "/reacquire")
    public ResponseResult<String> reacquireDeclaration(@RequestBody List<Long> orderIds) {
        Long companyId = ContextHolder.getContext().getCompanyId();
        orderDeclareClient.reacquireDeclaration(companyId, orderIds);
        return ResponseResult.success();
    }

    @ApiOperation("取消交运申报")
    @PostMapping(value = "/cancel")
    public ResponseResult<BatchExecuteResult<String>> cancelDeclare(@RequestBody List<Long> orderIds) {
        Long companyId = ContextHolder.getContext().getCompanyId();
        BatchExecuteResult<String> result = orderDeclareClient.cancelDeclare(companyId, orderIds);
        return ResponseResult.success(result);
    }

}
