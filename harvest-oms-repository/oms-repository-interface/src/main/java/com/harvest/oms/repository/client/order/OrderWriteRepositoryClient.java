package com.harvest.oms.repository.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.enums.oms.OrderStatusEnum;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:13 PM
 * @Description: TODO
 **/
@HarvestClient(value = HarvestOmsRepositoryApplications.SERVICE_NAME, path = HarvestOmsRepositoryApplications.Path.ORDER_WRITE)
public interface OrderWriteRepositoryClient extends GlobalMacroDefinition {

    @ApiOperation("保存订单")
    @PostMapping("/insert")
    void insert(@RequestParam(COMPANY_ID) Long companyId, @RequestBody OrderSimplePO orderSimple);

    @ApiOperation("保存订单明细")
    @PostMapping("/insertItems")
    void insertItems(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<OrderItemSimplePO> orderItemSimples);

    @ApiOperation("更新申报")
    @PostMapping("/updateDeclare")
    void updateDeclare(@RequestParam(COMPANY_ID) Long companyId, @RequestBody OrderSimplePO orderSimple);

    @ApiOperation("更新订单状态")
    @PostMapping("/updateOrderStatus")
    void updateOrderStatus(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId,
                           @RequestParam(OMS.ORDER_STATUS) OrderStatusEnum orderStatus);
}
