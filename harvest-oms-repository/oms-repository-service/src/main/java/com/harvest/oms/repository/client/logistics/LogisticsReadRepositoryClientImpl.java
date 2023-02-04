package com.harvest.oms.repository.client.logistics;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.logistics.OrderLogisticsKey;
import com.harvest.oms.repository.domain.logistics.simple.LogisticsChannelSimplePO;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:13 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsRepositoryApplications.LogisticsPath.LOGISTICS_READ)
public class LogisticsReadRepositoryClientImpl implements LogisticsReadRepositoryClient {

    @Override
    public Collection<LogisticsChannelSimplePO> getChanelByLogisticsKeys(Long companyId, List<OrderLogisticsKey> logisticsKeys) {
        return null;
    }

}
