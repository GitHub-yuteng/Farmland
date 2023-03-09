package com.harvest.wms.repository.client.warehouse;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.wms.repository.mapper.warehouse.read.WarehouseReadMapper;
import com.harvest.wms.repository.constants.HarvestWmsRepositoryApplications;
import com.harvest.wms.repository.domain.warehouse.simple.WarehouseSimplePO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/1/10 10:43 PM
 * @Description: 仓库读服务
 **/
@HarvestService(path = HarvestWmsRepositoryApplications.Path.WAREHOUSE_READ)
public class WarehouseReadRepositoryClientImpl implements WarehouseReadRepositoryClient {

    @Autowired
    private WarehouseReadMapper warehouseReadMapper;

    @Override
    public List<WarehouseSimplePO> getByCompanyId(Long companyId) {
        return warehouseReadMapper.getByCompanyId(companyId);
    }

    @Override
    public WarehouseSimplePO get(Long companyId, Long warehouseId) {
        return null;
    }
}
