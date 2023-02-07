package com.harvest.basic.web.controller.profile;

import com.harvest.basic.client.profile.WebConfigClient;
import com.harvest.basic.repository.enums.WebConfigEnum;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.domain.ResponseResult;
import com.harvest.core.path.HarvestBasicPath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Alodi
 * @Date: 2023/1/8 11:34 PM
 * @Description: TODO
 **/
@Api(value = "前端自定义配置服务", tags = "前端自定义配置服务")
@RestController
@RequestMapping(value = HarvestBasicPath.BasicPath.WEB_CONFIG)
public class WebConfigController implements GlobalMacroDefinition {

    @Autowired
    private WebConfigClient webConfigClient;

    @ApiOperation("提供前端自由配置")
    @GetMapping(value = "/get")
    public ResponseResult<String> get(@RequestParam("type") WebConfigEnum type) {
        return ResponseResult.success(webConfigClient.get(8510380986999420205L, type));
    }

    @ApiOperation("过去前端自由配置")
    @PostMapping(value = "/save")
    public ResponseResult<String> save(@RequestParam("type") WebConfigEnum type, @RequestParam("value") String value) {
        webConfigClient.save(8510380986999420205L, type, value);
        return ResponseResult.success();
    }

}
