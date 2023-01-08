package com.harvest.oms.client.order.rich;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.domain.Page;
import com.harvest.core.feign.annotation.HarvestClient;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderItemDO;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.vo.order.OrderInfoVO;
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

    @ApiOperation("订单信息查询")
    @PostMapping(value = "/pageQueryOrderRich")
    Page<OrderInfoVO> pageQueryOrderRich(@RequestParam(COMPANY_ID) Long companyId, @RequestBody PageOrderConditionQuery condition);

    @ApiOperation("订单明细信息查询")
    @PostMapping(value = "/queryOrderItemsRich")
    Collection<OrderItemDO> queryOrderItemsRich(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId);
}
