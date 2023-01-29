package com.harvest.oms.client.constants;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 5:18 PM
 * @Description: TODO
 **/
public interface HarvestOmsApplications {

    String SERVICE_NAME = "harvest-oms-service";

    String OMS_PATH = "/oms/order";

    interface Path {

        String ORDER_RICH = OMS_PATH + "/OrderRichQueryClient";
        String ORDER_READ = OMS_PATH + "/OrderReadClient";

    }

    interface LogisticsPath {

        String LOGISTICS_READ = OMS_PATH + "/LogisticsReadClient";

    }

    interface InboundPath {


    }

    interface OutboundPath {


    }
}
