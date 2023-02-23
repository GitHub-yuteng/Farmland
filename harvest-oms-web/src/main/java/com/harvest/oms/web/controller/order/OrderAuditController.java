package com.harvest.oms.web.controller.order;

import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.context.ContextHolder;
import com.harvest.core.domain.ResponseResult;
import com.harvest.core.path.HarvestOmsPath;
import com.harvest.oms.client.order.OrderAuditClient;
import com.harvest.oms.request.order.audit.SubmitAuditRequest;
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
 * @Description: 订单审核服务
 **/
@Api(value = "订单审核服务", tags = "订单审核服务")
@RestController
@RequestMapping(value = HarvestOmsPath.OrderPath.ORDER_AUDIT_PATH)
public class OrderAuditController implements GlobalMacroDefinition {

    @Autowired
    private OrderAuditClient orderAuditClient;

    @ApiOperation("订单审核")
    @PostMapping(value = "/check")
    public ResponseResult<BatchExecuteResult<String>> check(@RequestBody List<Long> orderIds) {
        Long companyId = ContextHolder.getContext().getCompanyId();
        BatchExecuteResult<String> result = orderAuditClient.check(companyId, orderIds);
        return ResponseResult.success(result);
    }

    @ApiOperation("订单审核")
    @PostMapping(value = "/exec")
    public ResponseResult<BatchExecuteResult<String>> audit(@RequestBody List<Long> orderIds) {
        Long companyId = ContextHolder.getContext().getCompanyId();
        BatchExecuteResult<String> result = orderAuditClient.audit(companyId, orderIds);
        return ResponseResult.success(result);
    }

    @ApiOperation("订单审核带提交项")
    @PostMapping(value = "/submit/exec")
    public ResponseResult<BatchExecuteResult<String>> auditWithSubmit(@RequestBody Collection<SubmitAuditRequest> requests) {
        Long companyId = ContextHolder.getContext().getCompanyId();
        BatchExecuteResult<String> result = orderAuditClient.auditWithSubmit(companyId, requests);
        return ResponseResult.success(result);
    }

}
