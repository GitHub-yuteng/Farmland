package com.harvest.oms.client.order;

import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import com.harvest.oms.vo.order.declare.OrderDeclarationVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description:
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_DECLARE)
public interface OrderDeclareClient extends GlobalMacroDefinition {

    @ApiOperation("查询订单申报信息")
    @PostMapping("/listDeclaration")
    Collection<OrderDeclarationVO> listDeclaration(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<Long> orderIds);

    @ApiOperation("订单并发发货申报")
    @PostMapping("/declare")
    BatchExecuteResult<String> declare(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<SubmitDeclarationRequest> requests);

    @ApiOperation("刷新申报信息")
    @PostMapping("/refreshDeclaration")
    void refreshDeclaration(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<Long> orderIds);

    @ApiOperation("取消交运申报")
    @PostMapping("/cancelDeclare")
    BatchExecuteResult<String> cancelDeclare(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<Long> orderIds);

    @ApiOperation("保存申报信息")
    @PostMapping("/saveDeclaration")
    void saveDeclaration(@RequestParam(COMPANY_ID) Long companyId, @RequestBody SubmitDeclarationRequest request);

    @ApiOperation("重新获取面单信息")
    @PostMapping("/reacquireDeclaration")
    void reacquireDeclaration(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<Long> orderIds);

    @ApiOperation("设置申报结果")
    @PostMapping("/saveDeclareResponse")
    void setLastResponse(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId, DeclarationResponse response);
}
