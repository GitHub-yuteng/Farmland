package com.harvest.wms.service.mapper.warehouse.read;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.wms.service.domain.warehouse.simple.WarehouseSimplePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/1/15 5:05 PM
 * @Description: TODO
 **/
@Mapper
public interface WarehouseReadMapper extends GlobalMacroDefinition {

    List<WarehouseSimplePO> getByCompanyId(@Param(COMPANY_ID) Long companyId);

}
