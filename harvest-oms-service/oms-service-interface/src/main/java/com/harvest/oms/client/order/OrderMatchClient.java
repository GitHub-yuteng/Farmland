package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.client.constants.HarvestOmsApplications;

/**
 * @Author: Alodi
 * @Date: 2023/02/21 9:48 PM
 * @Description: 订单匹配服务
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_MATCH)
public interface OrderMatchClient extends GlobalMacroDefinition {


}
