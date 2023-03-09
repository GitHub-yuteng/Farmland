package com.harvest.wms.client.warehouse;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.wms.service.constants.HarvestWmsApplications;
import com.harvest.wms.service.domain.warehouse.simple.WarehouseSimplePO;
import com.harvest.wms.service.repository.domain.WarehouseDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

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
    public Collection<WarehouseSimplePO> getByCompanyId(Long companyId) {
        return warehouseReadRepositoryClient.getByCompanyId(companyId);
    }

    @Override
    public WarehouseDO get(Long companyId, Long warehouseId) {
        WarehouseSimplePO warehouseSimplePO = warehouseReadRepositoryClient.get(companyId, warehouseId);
        return null;
    }

}
