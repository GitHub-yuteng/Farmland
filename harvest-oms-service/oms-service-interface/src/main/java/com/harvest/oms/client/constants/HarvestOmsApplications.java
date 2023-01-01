package com.harvest.oms.client.constants;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 5:18 PM
 * @Description: TODO
 **/
public interface HarvestOmsApplications {

    String SERVICE_NAME = "harvest-oms-service";

    interface Path {

        String OMS_PATH = "/oms/order";

        String ORDER_RICH = OMS_PATH + "/OrderRichQueryClient";
        String ORDER_READ = OMS_PATH + "/OrderReadClient";

    }
}
