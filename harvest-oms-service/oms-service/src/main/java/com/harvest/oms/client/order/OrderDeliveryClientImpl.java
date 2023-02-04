package com.harvest.oms.client.order;

import com.harvest.basic.client.logistics.BasicLogisticsClient;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 3:42 PM
 * @Description: 订单交运
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_DELIVERY)
public class OrderDeliveryClientImpl implements OrderDeliveryClient {

    @Autowired
    private BasicLogisticsClient basicLogisticsClient;

    @Override
    public void declare(long companyId) {
        basicLogisticsClient.submitDeclaration(companyId);
    }
}
