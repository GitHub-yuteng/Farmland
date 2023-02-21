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

        /**
         * 查询
         */
        String ORDER_FRONT = OMS_PATH + "/OrderFrontQueryClient";
        String ORDER_RICH  = OMS_PATH + "/OrderRichQueryClient";
        String ORDER_READ  = OMS_PATH + "/OrderReadClient";
        String ORDER_WRITE = OMS_PATH + "/OrderWriteClient";

        /**
         * 业务
         */
        String ORDER_AUDIT     = OMS_PATH + "/OrderAuditClient";
        String ORDER_MATCH     = OMS_PATH + "/OrderMatchClient";
        String ORDER_DECLARE   = OMS_PATH + "/OrderDeclareClient";
        String ORDER_DELIVERY  = OMS_PATH + "/OrderDeliveryClient";

    }

    interface LogisticsPath {

        String LOGISTICS_READ = OMS_PATH + "/LogisticsReadClient";

    }

    interface InboundPath {


    }

    interface OutboundPath {


    }
}
