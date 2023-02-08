package com.harvest.basic.client.logistics;

import com.harvest.basic.client.constants.HarvestBasicApplications;
import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 2:51 PM
 * @Description: TODO
 **/
@HarvestClient(name = HarvestBasicApplications.SERVICE_NAME, path = HarvestBasicApplications.Path.LOGISTICS)
public interface BasicLogisticsClient extends GlobalMacroDefinition {

    @ApiOperation("申报提交")
    @PostMapping("/submitDeclaration")
    DeclarationResponse submitDeclaration(@RequestParam(COMPANY_ID) Long companyId, @RequestBody SubmitDeclarationRequest request);

}
