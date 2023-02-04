package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.client.constants.HarvestOmsApplications;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description:
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_AUDIT)
public interface OrderAuditClient extends GlobalMacroDefinition {


}
