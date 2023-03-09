package com.harvest.oms.client;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.request.order.audit.SubmitAuditReturnRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/10 9:41 PM
 * @Description: 回调OMS服务
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.CALL_BACK_WMS)
public interface CallBackOrderClient extends GlobalMacroDefinition {

    @ApiOperation("并发打回订单审核")
    @PostMapping("/return/batch/audit")
    BatchExecuteResult<String> returnAudit(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<Long> orderIds);

    @ApiOperation("并发打回订单审核带提交项目")
    @PostMapping("/batch/return/audit/with/submit")
    BatchExecuteResult<String> returnAuditWithSubmit(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<SubmitAuditReturnRequest> requests);

    @ApiOperation("推送待分配状态")
    @PostMapping("/batch/allocated")
    BatchExecuteResult<String> allocate(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<Long> orderIds);

    @ApiOperation("推送打单配货状态")
    @PostMapping("/batch/print")
    BatchExecuteResult<String> print(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<Long> orderIds);

    @ApiOperation("推送待拣货状态")
    @PostMapping("/batch/collect")
    BatchExecuteResult<String> collect(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<Long> orderIds);

    @ApiOperation("推送检查状态")
    @PostMapping("/batch/check")
    BatchExecuteResult<String> check(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<Long> orderIds);

    @ApiOperation("推送打包状态")
    @PostMapping("/batch/pack")
    BatchExecuteResult<String> pack(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<Long> orderIds);
}
