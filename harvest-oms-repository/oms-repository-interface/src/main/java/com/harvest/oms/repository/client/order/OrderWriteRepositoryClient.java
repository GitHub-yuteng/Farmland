package com.harvest.oms.repository.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.enums.oms.OrderStatusEnum;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.order.base.OrderWarehouse;
import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.domain.order.update.remark.OrderUpdateRemark;
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

    @ApiOperation("更新订单备注")
    @PostMapping("/updateRemark")
    void updateRemark(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId,
                      @RequestBody OrderUpdateRemark remark);

    @ApiOperation("更新订单仓库")
    @PostMapping("/updateWarehouse")
    void updateWarehouse(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId,
                         @RequestBody OrderWarehouse orderWarehouse);

    @ApiOperation("提交异常")
    @PostMapping("/abnormal")
    void abnormal(@RequestParam(COMPANY_ID) Long companyId, @RequestParam("abnormal") boolean abnormal,
                  @RequestParam(OMS.ORDER_ID) Long orderId);

    @ApiOperation("移除全部订单标记")
    @PostMapping("/tagRemoveAll")
    void tagRemoveAll(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId);

    @ApiOperation("移除订单标记")
    @PostMapping("/tagRemove")
    void tagRemove(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId,
                   @RequestBody List<Integer> tagValues);

}
