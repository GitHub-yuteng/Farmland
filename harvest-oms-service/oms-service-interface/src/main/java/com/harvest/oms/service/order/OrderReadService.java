package com.harvest.oms.service.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.repository.domain.order.OrderInfoDO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description:
 **/
@HarvestClient(value = "harvest-oms-service")
public interface OrderReadService extends GlobalMacroDefinition {

    @ApiOperation("查询订单信息")
    @RequestMapping(value = "/order/read/get")
    OrderInfoDO getOrderInfo(@RequestParam(OMS.ORDER_ID) Long orderId);

}
