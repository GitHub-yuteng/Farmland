package com.harvest.oms.client.logistics;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.logistics.OrderLogisticsChannelDO;
import com.harvest.oms.repository.domain.logistics.OrderLogisticsKey;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description:
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.LogisticsPath.LOGISTICS_READ)
public interface OrderLogisticsReadClient extends GlobalMacroDefinition {

    @ApiOperation("订单物流渠道查询")
    @PostMapping(value = "/getChanelByLogisticsKeys")
    Collection<OrderLogisticsChannelDO> getChanelByLogisticsKeys(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<OrderLogisticsKey> logisticsKeys);

}
