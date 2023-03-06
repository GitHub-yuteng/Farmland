package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.request.order.audit.SubmitAuditRequest;
import com.harvest.oms.request.order.audit.SubmitAuditReturnRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description:
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_AUDIT)
public interface OrderAuditClient extends GlobalMacroDefinition {

    @ApiOperation("订单并发审核检查")
    @PostMapping("/batch/audit/check")
    BatchExecuteResult<String> check(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<Long> orderIds);

    @ApiOperation("并发订单审核")
    @PostMapping("/batch/audit")
    BatchExecuteResult<String> audit(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<Long> orderIds);

    @ApiOperation("并发订单审核带提交项目")
    @PostMapping("/batch/audit/with/submit")
    BatchExecuteResult<String> auditWithSubmit(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<SubmitAuditRequest> requests);

    @ApiOperation("并发打回订单审核")
    @PostMapping("/return/batch/audit")
    BatchExecuteResult<String> returnAudit(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<Long> orderIds);

    @ApiOperation("并发打回订单审核带提交项目")
    @PostMapping("/return/batch/audit/with/submit")
    BatchExecuteResult<String> returnAuditWithSubmit(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<SubmitAuditReturnRequest> requests);
}
