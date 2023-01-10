package com.harvest.wms.repository.client.warehouse;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.feign.annotation.HarvestClient;
import com.harvest.wms.repository.constants.HarvestWmsRepositoryApplications;
import com.harvest.wms.repository.domain.warehouse.simple.WarehouseSimplePO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/1/10 10:42 PM
 * @Description: TODO
 **/
@HarvestClient(value = HarvestWmsRepositoryApplications.SERVICE_NAME, path = HarvestWmsRepositoryApplications.Path.WAREHOUSE_READ)
public interface WarehouseReadRepositoryClient extends GlobalMacroDefinition {

    @ApiOperation("查询公司下所有仓库")
    @PostMapping(value = "/getByCompanyId")
    List<WarehouseSimplePO> getByCompanyId(Long companyId);

    WarehouseSimplePO get(Long companyId, Long warehouseId);
}
