package com.harvest.oms.client.logistics;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.logistics.LogisticsChannelDO;
import com.harvest.oms.repository.client.logistics.LogisticsReadRepositoryClient;
import com.harvest.oms.repository.domain.logistics.OrderLogisticsKey;
import com.harvest.oms.repository.domain.logistics.simple.LogisticsChannelSimplePO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 10:24 PM
 * @Description: 物流读服务
 **/
@HarvestService(path = HarvestOmsApplications.LogisticsPath.LOGISTICS_READ)
public class LogisticsReadClientImpl implements LogisticsReadClient {

    @Autowired
    private LogisticsReadRepositoryClient logisticsReadRepositoryClient;

    @Override
    public Collection<LogisticsChannelDO> getChanelByLogisticsKeys(Long companyId, List<OrderLogisticsKey> logisticsKeys) {
        Collection<LogisticsChannelSimplePO> channelSimpleList = logisticsReadRepositoryClient.getChanelByLogisticsKeys(companyId, logisticsKeys);
        return channelSimpleList.stream().map(simple -> {
            LogisticsChannelDO logisticsChannel = new LogisticsChannelDO();
            BeanUtils.copyProperties(simple, logisticsChannel);
            return logisticsChannel;
        }).collect(Collectors.toList());
    }

}
