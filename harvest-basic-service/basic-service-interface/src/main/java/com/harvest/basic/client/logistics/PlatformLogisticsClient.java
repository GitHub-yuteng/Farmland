package com.harvest.basic.client.logistics;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2023/2/4 1:36 PM
 * @Description: TODO
 **/
public interface PlatformLogisticsClient extends GlobalMacroDefinition {

    @ApiOperation("申报提交")
    @PostMapping("/submitDeclaration")
    void submitDeclaration(@RequestParam(COMPANY_ID) Long companyId, @RequestBody SubmitDeclarationRequest request);

    @ApiOperation("获取面单")
    @PostMapping("/print")
    void print(@RequestParam(COMPANY_ID) Long companyId);
}
