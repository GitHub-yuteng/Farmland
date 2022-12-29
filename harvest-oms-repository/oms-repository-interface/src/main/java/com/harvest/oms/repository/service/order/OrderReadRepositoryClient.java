package com.harvest.oms.repository.service.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.repository.domain.order.OrderInfoDO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:13 PM
 * @Description: TODO
 **/
@HarvestClient(value = "harvest-oms-read-repository")
public interface OrderReadRepositoryClient extends GlobalMacroDefinition {

    @ApiOperation("查询订单信息")
    @PostMapping(value = "/order/read/get")
    OrderInfoDO getOrderInfo(@RequestParam(OMS.ORDER_ID) Long orderId);
}
