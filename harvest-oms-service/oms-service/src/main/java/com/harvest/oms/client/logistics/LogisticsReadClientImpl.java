package com.harvest.oms.client.logistics;

import com.harvest.core.feign.annotation.HarvestService;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.logistics.OrderLogisticsChannelDO;
import com.harvest.oms.domain.order.logistics.OrderLogisticsKey;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 10:24 PM
 * @Description: 物流读服务
 **/
@HarvestService(path = HarvestOmsApplications.LogisticsPath.LOGISTICS_READ)
public class LogisticsReadClientImpl implements LogisticsReadClient {

    @Override
    public Collection<OrderLogisticsChannelDO> getChanelByLogisticsKeys(Long companyId, List<OrderLogisticsKey> logisticsKeys) {
        return null;
    }

}
