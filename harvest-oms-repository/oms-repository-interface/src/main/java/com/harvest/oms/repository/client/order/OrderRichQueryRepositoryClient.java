package com.harvest.oms.repository.client.order;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.domain.Page;
import com.harvest.core.feign.annotation.HarvestClient;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 4:42 PM
 * @Description: 订单丰富查询服务
 **/
@HarvestClient(value = HarvestOmsRepositoryApplications.SERVICE_NAME, path = HarvestOmsRepositoryApplications.Path.ORDER_RICH)
public interface OrderRichQueryRepositoryClient extends GlobalMacroDefinition {

    /**
     * 一次查询, 查询出所有简要信息
     *
     * @param companyId
     * @param condition
     * @return
     */
    @ApiOperation("订单信息查询")
    @PostMapping(value = "/pageQueryOrderRich")
    Page<OrderSimplePO> pageQueryOrderRich(@RequestParam(COMPANY_ID) Long companyId, @RequestBody PageOrderConditionQuery condition);

}
