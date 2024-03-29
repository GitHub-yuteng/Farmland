package com.harvest.oms.repository.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.declare.OrderDeclareSimplePO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:13 PM
 * @Description: TODO
 **/
@HarvestClient(value = HarvestOmsRepositoryApplications.SERVICE_NAME, path = HarvestOmsRepositoryApplications.Path.ORDER_DECLARE)
public interface OrderDeclareRepositoryClient extends GlobalMacroDefinition {

    @ApiOperation("查询报关信息")
    @PostMapping("/listDeclaration")
    Collection<OrderDeclareSimplePO> listDeclaration(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<Long> orderIds);

    @ApiOperation("保存草稿申报信息")
    @PostMapping("/draftDeclaration")
    void draftDeclaration(@RequestParam(COMPANY_ID) Long companyId, @RequestBody OrderDeclareSimplePO orderDeclareSimple);

    @ApiOperation("删除订单报关信息")
    @PostMapping("/deleteDeclarationByOrderId")
    void deleteDeclarationByOrderId(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId);

    @ApiOperation("设置申报结果")
    @PostMapping("/setLastResponse")
    void setLastResponse(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId, @RequestBody Object response);

}
