package com.harvest.basic.client.logistics;

import com.harvest.basic.domain.logistics.DeclarationDataFile;
import com.harvest.basic.domain.logistics.DeclarationResponse;
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

    String MESSAGE = "暂不支持该功能!";

    @ApiOperation("获取授权")
    @PostMapping("/token")
    void getToken(@RequestParam(COMPANY_ID) Long companyId);

    @ApiOperation("刷新授权")
    @PostMapping("/refreshToken")
    void refreshToken(@RequestParam(COMPANY_ID) Long companyId);

    @PostMapping("/validAccount")
    @ApiOperation(value = "账户验证")
    void validAccount(@RequestParam(COMPANY_ID) Long companyId);

    @ApiOperation("提交申报")
    @PostMapping("/submitDeclaration")
    DeclarationResponse submitDeclaration(@RequestParam(COMPANY_ID) Long companyId, @RequestBody SubmitDeclarationRequest request);

    @ApiOperation("获取运单号")
    @PostMapping("/getDeliveryNo")
    void getDeliveryNo(@RequestParam(COMPANY_ID) Long companyId, @RequestBody SubmitDeclarationRequest request);

    @ApiOperation("获取面单")
    @PostMapping("/print")
    DeclarationDataFile print(@RequestParam(COMPANY_ID) Long companyId, @RequestBody SubmitDeclarationRequest request);

    @ApiOperation("取消申报")
    @PostMapping("/cancelDeclaration")
    void cancelDeclaration(@RequestParam(COMPANY_ID) Long companyId, @RequestBody SubmitDeclarationRequest request);

    @ApiOperation("查询揽收时间")
    @PostMapping("/queryPickTime")
    void queryPickTime(@RequestParam(COMPANY_ID) Long companyId);
}
