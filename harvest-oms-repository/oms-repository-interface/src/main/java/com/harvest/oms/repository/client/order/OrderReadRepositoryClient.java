package com.harvest.oms.repository.client.order;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
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
@HarvestClient(value = HarvestOmsRepositoryApplications.SERVICE_NAME, path = HarvestOmsRepositoryApplications.Path.ORDER_READ)
public interface OrderReadRepositoryClient extends GlobalMacroDefinition {

    @PostMapping(value = "/read/get")
    OrderSimplePO get(@RequestParam(COMPANY_ID) Long companyId, @RequestParam(OMS.ORDER_ID) Long orderId);

    @PostMapping(value = "/read/get/items")
    Collection<OrderItemSimplePO> getOrderItemByOrderId(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Long orderId);

    @PostMapping(value = "/read/list/item")
    Collection<OrderItemSimplePO> listOrderItemByOrderIds(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<Long> orderIds);
}
