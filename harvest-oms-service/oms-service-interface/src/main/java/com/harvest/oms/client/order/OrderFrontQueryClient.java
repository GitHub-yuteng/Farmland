package com.harvest.oms.client.order;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.domain.Page;
import com.harvest.core.feign.annotation.HarvestClient;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.vo.order.OrderInfoVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description: 前端订单查询服务
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_FRONT)
public interface OrderFrontQueryClient extends GlobalMacroDefinition {

    @ApiOperation("分页订单信息查询-特定供前端调用-查询触发器")
    @PostMapping(value = "/front/page/order")
    Page<OrderInfoVO> frontPageQueryOrder(@RequestParam(COMPANY_ID) Long companyId, @RequestBody PageOrderConditionQuery condition);

}
