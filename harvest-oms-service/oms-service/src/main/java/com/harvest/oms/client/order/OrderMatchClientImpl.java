package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.service.order.AbstractBizOrderService;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 3:42 PM
 * @Description: 订单审核
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_MATCH)
public class OrderMatchClientImpl extends AbstractBizOrderService implements OrderMatchClient {


}
