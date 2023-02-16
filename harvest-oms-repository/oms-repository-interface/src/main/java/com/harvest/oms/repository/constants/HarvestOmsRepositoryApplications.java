package com.harvest.oms.repository.constants;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 5:18 PM
 * @Description: TODO
 **/
public interface HarvestOmsRepositoryApplications {

    String SERVICE_NAME = "harvest-oms-repository";

    String OMS_PATH = "/oms/repository/order";

    interface Path {

        String ORDER_RICH    = OMS_PATH + "/OrderRichQueryRepositoryClient";
        String ORDER_READ    = OMS_PATH + "/OrderReadRepositoryClient";
        String ORDER_DECLARE = OMS_PATH + "/OrderDeclareRepositoryClient";
        String ORDER_WRITE   = OMS_PATH + "/OrderWriteRepositoryClient";

    }

    interface LogisticsPath {

        String LOGISTICS_READ = OMS_PATH + "/LogisticsReadClient";

    }
}
