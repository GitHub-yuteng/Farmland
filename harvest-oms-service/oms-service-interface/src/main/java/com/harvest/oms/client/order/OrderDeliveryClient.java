package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/03/10 9:48 PM
 * @Description:
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_DELIVERY)
public interface OrderDeliveryClient extends GlobalMacroDefinition {

    @ApiOperation("订单发货")
    @PostMapping("/delivery")
    BatchExecuteResult<String> delivery(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<Long> orderIds);

}
