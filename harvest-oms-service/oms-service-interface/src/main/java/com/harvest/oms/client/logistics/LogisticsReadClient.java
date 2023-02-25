package com.harvest.oms.client.logistics;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.logistics.LogisticsChannelAddressDO;
import com.harvest.oms.domain.logistics.LogisticsChannelDO;
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
public interface LogisticsReadClient extends GlobalMacroDefinition {

    @ApiOperation("查询物流渠道")
    @PostMapping(value = "/getChanelByLogisticsKeys")
    Collection<LogisticsChannelDO> getChannelByLogisticsKeys(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<OrderLogisticsKey> logisticsKeys);

    @ApiOperation("查询物流渠道地址")
    @PostMapping(value = "/getChannelAddress")
    List<LogisticsChannelAddressDO> getChannelAddress(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.CHANNEL_ID) Long channelId);

    @ApiOperation("查询物流渠道地址")
    @PostMapping(value = "/getAuthorization")
    String getAuthorization(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(LOGISTICS.LOGISTICS_TYPE) LogisticsEnum logisticsType);
}
