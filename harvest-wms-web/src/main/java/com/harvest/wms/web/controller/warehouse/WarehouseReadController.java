package com.harvest.wms.web.controller.warehouse;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.domain.ResponseResult;
import com.harvest.core.path.HarvestWmsPath;
import com.harvest.wms.client.warehouse.WarehouseReadClient;
import com.harvest.wms.repository.domain.warehouse.simple.WarehouseSimplePO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:51 PM
 * @Description: 仓库读服务
 **/
@Api(value = "仓库读服务", tags = "仓库读服务")
@RestController
@RequestMapping(value = HarvestWmsPath.WarehousePath.WAREHOUSE_READ_PATH)
public class WarehouseReadController implements GlobalMacroDefinition {

    @Autowired
    private WarehouseReadClient warehouseReadClient;

    @ApiOperation("仓库查询")
    @RequestMapping(value = "/getByCompanyId")
    public ResponseResult<Collection<WarehouseSimplePO>> getByCompanyId(@RequestParam(COMPANY_ID) Long companyId) {
        return ResponseResult.success(warehouseReadClient.getByCompanyId(companyId));
    }

}
