package com.harvest.oms.service.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.repository.domain.order.OrderInfoDO;
import com.harvest.oms.service.constants.HarvestOmsApplications;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description:
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_READ)
public interface OrderReadClient extends GlobalMacroDefinition {

    @ApiOperation("查询订单信息")
    @PostMapping(value = "/get")
    OrderInfoDO get(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId);

}
