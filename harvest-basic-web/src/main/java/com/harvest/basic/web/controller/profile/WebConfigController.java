package com.harvest.basic.web.controller.profile;

import com.harvest.basic.client.profile.WebConfigClient;
import com.harvest.basic.repository.enums.WebConfigEnum;
import com.harvest.core.domain.ResponseResult;
import com.harvest.core.path.HarvestBasicPath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Alodi
 * @Date: 2023/1/8 11:34 PM
 * @Description: TODO
 **/
@Api(value = "前端自定义配置服务", tags = "前端自定义配置服务")
@RestController
@RequestMapping(value = HarvestBasicPath.BasicPath.WEB_CONFIG)
public class WebConfigController {

    private WebConfigClient webConfigClient;

    /**
     * 前端项目控制配置项目
     *
     * @param
     * @return
     */
    @ApiOperation("提供前端自由配置")
    @RequestMapping(value = "/get")
    public ResponseResult<String> get(@RequestBody WebConfigEnum webConfig) {
        Long companyId = 1L;
        return ResponseResult.success(webConfigClient.get(companyId, webConfig));
    }

}
