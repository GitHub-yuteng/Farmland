package com.harvest.wms.client.warehouse;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.wms.service.constants.HarvestWmsRepositoryApplications;
import com.harvest.wms.service.domain.warehouse.simple.WarehouseSimplePO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    List<WarehouseSimplePO> getByCompanyId(@RequestParam(COMPANY_ID) Long companyId);

    @ApiOperation("查询公司下所有仓库")
    @PostMapping(value = "/get")
    WarehouseSimplePO get(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(WMS.WAREHOUSE_ID) Long warehouseId);
}
