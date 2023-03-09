package com.harvest.wms.client.callback.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.request.order.audit.SubmitAuditReturnRequest;
import com.harvest.wms.service.constants.HarvestWmsApplications;
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
@HarvestClient(name = HarvestWmsApplications.SERVICE_NAME, path = HarvestWmsApplications.Path.WAREHOUSE_READ)
public interface CallBackOrderClient extends GlobalMacroDefinition {

    @ApiOperation("并发打回订单审核")
    @PostMapping("/return/batch/audit")
    BatchExecuteResult<String> returnAudit(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<Long> orderIds);

    @ApiOperation("并发打回订单审核带提交项目")
    @PostMapping("/return/batch/audit/with/submit")
    BatchExecuteResult<String> returnAuditWithSubmit(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<SubmitAuditReturnRequest> requests);

}
