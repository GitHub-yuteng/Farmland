package com.harvest.core.path;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:16 PM
 * @Description: 订单路径定义
 **/
public interface HarvestOmsPath {

    String OMS_PATH = "/oms/order";

    /**
     * 订单路径
     */
    interface OrderPath {

        String OMS_RICH_PATH = OMS_PATH + "/rich";
        String OMS_BUSINESS_PATH = OMS_PATH + "/business";
        String OMS_READ_PATH = OMS_PATH + "/read";
        String OMS_WRITE_PATH = OMS_PATH + "/write";

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
