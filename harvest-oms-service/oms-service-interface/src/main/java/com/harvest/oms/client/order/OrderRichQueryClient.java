package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.domain.Page;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.OrderItemDO;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description: 订单丰富查询服务
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_RICH)
public interface OrderRichQueryClient extends GlobalMacroDefinition {

    @ApiOperation("分页订单信息查询")
    @PostMapping(value = "/pageQueryOrder")
    Page<OrderInfoDO> pageQueryOrder(@RequestParam(COMPANY_ID) Long companyId, @RequestBody PageOrderConditionQuery condition);

    @ApiOperation("订单信息查询")
    @PostMapping(value = "/getOrder")
    OrderInfoDO getOrder(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId);


    /*丰富信息查询*/

    @ApiOperation("分页订单丰富信息查询")
    @PostMapping(value = "/pageQueryOrderRich")
    Page<OrderInfoDO> pageQueryOrderRich(@RequestParam(COMPANY_ID) Long companyId, @RequestBody PageOrderConditionQuery condition);

    @ApiOperation("订单丰富信息查询")
    @PostMapping(value = "/getOrderRich")
    OrderInfoDO getOrderRich(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId);

    @ApiOperation("集合订单信息查询")
    @PostMapping(value = "/listQueryOrderRich")
    Collection<OrderInfoDO> listQueryOrderRich(@RequestParam(COMPANY_ID) Long companyId, @RequestBody PageOrderConditionQuery condition);

    @ApiOperation("订单明细信息查询")
    @PostMapping(value = "/queryOrderItemsRich")
    Collection<OrderItemDO> queryOrderItemsRich(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId);
}
