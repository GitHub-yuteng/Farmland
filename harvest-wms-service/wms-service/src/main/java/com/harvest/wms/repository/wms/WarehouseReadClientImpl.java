package com.harvest.wms.repository.wms;

import com.harvest.core.feign.annotation.HarvestService;
import com.harvest.wms.repository.client.warehouse.WarehouseReadClient;
import com.harvest.wms.repository.client.warehouse.WarehouseReadRepositoryClient;
import com.harvest.wms.repository.constants.HarvestWmsApplications;
import com.harvest.wms.repository.domain.WarehouseDO;
import com.harvest.wms.repository.domain.warehouse.simple.WarehouseSimplePO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/1/10 9:35 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestWmsApplications.Path.WAREHOUSE_READ)
public class WarehouseReadClientImpl implements WarehouseReadClient {

    @Autowired
    private WarehouseReadRepositoryClient warehouseReadRepositoryClient;

    @Override
    public List<WarehouseDO> getByCompanyId(Long companyId) {
        List<WarehouseSimplePO> warehouseSimpleList = warehouseReadRepositoryClient.getByCompanyId(companyId);
        return null;
    }

    @Override
    public WarehouseDO get(Long companyId, Long warehouseId) {
        WarehouseSimplePO warehouseSimplePO = warehouseReadRepositoryClient.get(companyId, warehouseId);
        return null;
    }

}
