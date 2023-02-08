package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description:
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_DELIVERY)
public interface OrderDeliveryClient extends GlobalMacroDefinition {

    @ApiOperation("查询订单申报信息")
    @PostMapping("/listDeclaration")
    void listDeclaration(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<Long> orderIds);

    @ApiOperation("订单并发发货申报")
    @PostMapping("/declare")
    BatchExecuteResult<String> declare(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<SubmitDeclarationRequest> requests);


}
