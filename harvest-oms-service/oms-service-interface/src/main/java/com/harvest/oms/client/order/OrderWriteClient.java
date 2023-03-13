package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderInfoDO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description: 订单写服务
 **/
@HarvestClient(name = HarvestOmsApplications.SERVICE_NAME, path = HarvestOmsApplications.Path.ORDER_WRITE)
public interface OrderWriteClient extends GlobalMacroDefinition {

    @PostMapping("/build")
    void build(@RequestParam(COMPANY_ID) Long companyId);

    @PostMapping("/saveOrder")
    void saveOrder(@RequestParam(COMPANY_ID) Long companyId, @RequestBody OrderInfoDO order);

    @PostMapping("/updateDeclare")
    void updateDeclare(@RequestParam(COMPANY_ID) Long companyId, @RequestBody OrderInfoDO order);

    @PostMapping("/updateOrderStatus")
    void updateOrderStatus(@RequestParam(COMPANY_ID) Long companyId, @RequestBody OrderInfoDO order);

}
