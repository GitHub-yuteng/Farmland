package com.harvest.core.path;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:16 PM
 * @Description: 订单路径定义
 **/
public interface HarvestOmsPath {

    String BASIC_PATH           = "/harvest/basic";
    String GOODS_PATH           = "/harvest/goods";
    String FINANCE_PATH         = "/harvest/finance";
    String OMS_PATH             = "/harvest/oms/order";
    String OMS_INBOUND_PATH     = "/harvest/oms/inbound";
    String OMS_OUTBOUND_PATH    = "/harvest/oms/outbound";

    /**
     * 基础模块路径
     */
    interface BasicPath {

        String WEB_CONFIG = BASIC_PATH + "/webConfig";
    }

    /**
     * 基础模块路径
     */
    interface GoodsPath {

        String GOODS_READ_PATH = GOODS_PATH + "/read";
        String GOODS_WRITE_PATH = GOODS_PATH + "/write";
    }

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
