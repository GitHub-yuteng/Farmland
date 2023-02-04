package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description:
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_DELIVERY)
public interface OrderDeliveryClient extends GlobalMacroDefinition {


    @ApiOperation("订单发货申报")
    @PostMapping("/declare")
    void declare(@RequestParam(COMPANY_ID) long companyId);

}
