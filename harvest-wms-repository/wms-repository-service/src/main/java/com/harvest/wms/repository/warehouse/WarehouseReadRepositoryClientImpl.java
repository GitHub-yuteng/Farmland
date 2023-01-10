package com.harvest.wms.repository.warehouse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.harvest.core.enums.wms.WarehouseOwnerEnum;
import com.harvest.core.enums.wms.WarehouseTypeEnum;
import com.harvest.core.feign.annotation.HarvestService;
import com.harvest.wms.repository.client.warehouse.WarehouseReadRepositoryClient;
import com.harvest.wms.repository.constants.HarvestWmsRepositoryApplications;
import com.harvest.wms.repository.domain.warehouse.simple.WarehouseSimplePO;
import com.harvest.wms.repository.entity.FarmlandWmsWarehouseEntity;
import com.harvest.wms.repository.mapper.FarmlandWmsWarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: Alodi
 * @Date: 2023/1/10 10:43 PM
 * @Description: 仓库读服务
 **/
@HarvestService(path = HarvestWmsRepositoryApplications.Path.WAREHOUSE_READ)
public class WarehouseReadRepositoryClientImpl implements WarehouseReadRepositoryClient {

    @Autowired
    private FarmlandWmsWarehouseMapper farmlandWmsWarehouseMapper;

    @Override
    public List<WarehouseSimplePO> getByCompanyId(Long companyId) {
        return farmlandWmsWarehouseMapper.selectList(new QueryWrapper<FarmlandWmsWarehouseEntity>().lambda()
                        .eq(FarmlandWmsWarehouseEntity::getCompanyId, companyId)
                        .in(FarmlandWmsWarehouseEntity::getStatus, Stream.of(1))
                        .eq(FarmlandWmsWarehouseEntity::getIsDeleted, Switch.OFF))
                .stream().map(item -> {
                    WarehouseSimplePO warehouseSimplePO = new WarehouseSimplePO();
                    warehouseSimplePO.setMainType(item.getMainType());
                    warehouseSimplePO.setIsDelivery(item.getIsDelivery());
                    warehouseSimplePO.setStatus(item.getStatus());
                    warehouseSimplePO.setWarehouseId(item.getId());
                    warehouseSimplePO.setWarehouseCode(item.getWarehouseCode());
                    warehouseSimplePO.setWarehouse(item.getWarehouseName());
                    warehouseSimplePO.setWarehouseOwner(WarehouseOwnerEnum.getEnumByType(item.getWarehouseOwner()));
                    warehouseSimplePO.setWarehouseType(WarehouseTypeEnum.getEnumByType(item.getWarehouseType()));
                    warehouseSimplePO.setCompanyId(companyId);
                    return warehouseSimplePO;
                }).collect(Collectors.toList());
    }

    @Override
    public WarehouseSimplePO get(Long companyId, Long warehouseId) {
        return null;
    }
}
