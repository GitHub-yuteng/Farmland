package com.harvest.oms.service.order.rich;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.domain.Page;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.service.constants.HarvestOmsApplications;
import com.harvest.oms.vo.order.OrderInfoVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description:
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_RICH)
public interface OrderRichQueryClient extends GlobalMacroDefinition {


    @ApiOperation("订单信息查询")
    @PostMapping(value = "/pageQueryOrderRich")
    Page<OrderInfoVO> pageQueryOrderRich(@RequestParam(COMPANY_ID) Long companyId, @RequestBody PageOrderConditionQuery pageOrderConditionQuery);

}
