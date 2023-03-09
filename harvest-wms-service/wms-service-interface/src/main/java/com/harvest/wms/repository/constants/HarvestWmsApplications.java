package com.harvest.wms.repository.constants;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 5:18 PM
 * @Description: TODO
 **/
public interface HarvestWmsApplications {

    String SERVICE_NAME = "harvest-wms-service";

    interface Path {

        String WMS_PATH = "/wms";

        String WAREHOUSE_READ = WMS_PATH + "/WarehouseReadClient";

        String CALL_BACK_ORDER = WMS_PATH + "/CallBackOrderClient";

    }
}
