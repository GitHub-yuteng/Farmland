package com.harvest.wms.repository.client.warehouse;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.feign.annotation.HarvestClient;
import com.harvest.wms.repository.constants.HarvestWmsApplications;
import com.harvest.wms.repository.domain.WarehouseDO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/1/10 9:41 PM
 * @Description: TODO
 **/
@HarvestClient(name = HarvestWmsApplications.SERVICE_NAME, path = HarvestWmsApplications.Path.WAREHOUSE_READ)
public interface WarehouseReadClient extends GlobalMacroDefinition {

    @ApiOperation("查询公司下所有仓库")
    @PostMapping(value = "/getByCompanyId")
    List<WarehouseDO> getByCompanyId(@RequestParam(COMPANY_ID) Long companyId);

    @ApiOperation("查询仓库信息")
    @PostMapping(value = "/get")
    WarehouseDO get(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(WMS.WAREHOUSE_ID) Long warehouseId);
}
