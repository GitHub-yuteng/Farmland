package com.harvest.core.path;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:16 PM
 * @Description: 订单路径定义
 **/
public interface HarvestOmsPath {

    /**
     * 订单路径
     */
    interface OrderPath {

        String OMS_BUSINESS_PATH = "/order/business";
        String OMS_READ_PATH = "/order/read";
        String OMS_WRITE_PATH = "/order/write";
        
    }
}
