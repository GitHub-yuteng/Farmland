package com.harvest.core.path;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:16 PM
 * @Description: 订单路径定义
 **/
public interface HarvestWmsPath {

    String WMS_PATH = "/harvest/wms";


    /**
     * 订单路径
     */
    interface WarehousePath {

        String WAREHOUSE_READ_PATH = WMS_PATH + "/read/warehouse";

    }

}
