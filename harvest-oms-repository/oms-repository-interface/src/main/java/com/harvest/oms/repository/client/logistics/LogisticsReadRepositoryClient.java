package com.harvest.oms.repository.client.logistics;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.logistics.OrderLogisticsKey;
import com.harvest.oms.repository.domain.logistics.simple.LogisticsChannelSimplePO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:13 PM
 * @Description: TODO
 **/
@HarvestClient(value = HarvestOmsRepositoryApplications.SERVICE_NAME, path = HarvestOmsRepositoryApplications.LogisticsPath.LOGISTICS_READ)
public interface LogisticsReadRepositoryClient extends GlobalMacroDefinition {

    @ApiOperation("订单物流渠道查询")
    @PostMapping(value = "/getChanelByLogisticsKeys")
    Collection<LogisticsChannelSimplePO> getChanelByLogisticsKeys(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<OrderLogisticsKey> logisticsKeys);

}
