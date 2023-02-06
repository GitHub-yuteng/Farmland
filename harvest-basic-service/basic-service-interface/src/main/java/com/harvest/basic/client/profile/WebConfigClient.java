package com.harvest.basic.client.profile;

import com.harvest.basic.client.constants.HarvestBasicApplications;
import com.harvest.basic.repository.enums.WebConfigEnum;
import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2023/1/8 11:40 PM
 * @Description: 提供前端自由配置
 **/
@HarvestClient(name = HarvestBasicApplications.SERVICE_NAME, path = HarvestBasicApplications.Path.WEB_CONFIG)
public interface WebConfigClient extends GlobalMacroDefinition {

    @ApiOperation("提供前端自由配置")
    @PostMapping(value = "/get")
    String get(@RequestParam(COMPANY_ID) Long companyId, @RequestParam("type") WebConfigEnum type);

    @ApiOperation("保存前端配置")
    @PostMapping(value = "/save")
    void save(@RequestParam(COMPANY_ID) Long companyId, @RequestParam("type") WebConfigEnum type, @RequestParam("value") String value);

}
