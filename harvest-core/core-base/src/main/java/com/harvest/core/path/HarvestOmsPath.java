package com.harvest.core.path;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:16 PM
 * @Description: OMS路径定义
 **/
public interface HarvestOmsPath {

    String OMS_PATH             = "/harvest/oms";

    /**
     * 订单路径
     */
    interface OrderPath {

        String OMS_ORDER_PATH       = OMS_PATH + "/order";

        String ORDER_FRONT_PATH     = OMS_ORDER_PATH + "/front";
        String ORDER_BUSINESS_PATH  = OMS_ORDER_PATH + "/business";
        String ORDER_AUDIT_PATH     = OMS_ORDER_PATH + "/audit";
        String ORDER_DELIVERY_PATH  = OMS_ORDER_PATH + "/delivery";
        String ORDER_READ_PATH      = OMS_ORDER_PATH + "/read";
        String ORDER_WRITE_PATH     = OMS_ORDER_PATH + "/write";

    }

    /**
     * 入库单路径
     */
    interface InboundOrderPath {

        String OMS_INBOUND_PATH     = "/harvest/oms/inbound";


    }

    /**
     * 出库单路径
     */
    interface OutboundOrderPath {

        String OMS_OUTBOUND_PATH    = "/harvest/oms/outbound";


    }
}
