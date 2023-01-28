package com.harvest.core.path;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:16 PM
 * @Description: OMS路径定义
 **/
public interface HarvestOmsPath {

    String FINANCE_PATH         = "/harvest/finance";
    String OMS_ORDER_PATH       = "/harvest/oms/order";
    String OMS_INBOUND_PATH     = "/harvest/oms/inbound";
    String OMS_OUTBOUND_PATH    = "/harvest/oms/outbound";

    /**
     * 订单路径
     */
    interface OrderPath {

        String ORDER_RICH_PATH = OMS_ORDER_PATH + "/rich";
        String ORDER_BUSINESS_PATH = OMS_ORDER_PATH + "/business";
        String ORDER_READ_PATH = OMS_ORDER_PATH + "/read";
        String ORDER_WRITE_PATH = OMS_ORDER_PATH + "/write";

    }

    /**
     * 入库单路径
     */
    interface InboundOrderPath {


    }

    /**
     * 出库单路径
     */
    interface OutboundOrderPath {


    }
}
