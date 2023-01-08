package com.harvest.oms.client.order;

import com.harvest.core.feign.annotation.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    @ApiOperation("查询订单信息")
    @PostMapping(value = "/listOrderByOrderIds")
    Collection<OrderInfoDO> listOrderByOrderIds(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<Long> orderIds);

    @ApiOperation("订单明细信息查询")
    @PostMapping(value = "/listOrderItemByOrderIds")
    Collection<OrderItemSimplePO> listOrderItemByOrderId(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId);

    @ApiOperation("订单明细信息查询")
    @PostMapping(value = "/mapOrderItemByOrderIds")
    Map<Long, List<OrderItemSimplePO>> mapOrderItemByOrderIds(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<Long> orderIds);

}
