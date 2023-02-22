package com.harvest.oms.web.controller.order;

import com.harvest.core.context.ContextHolder;
import com.harvest.core.path.HarvestOmsPath;
import com.harvest.oms.client.order.OrderWriteClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:51 PM
 * @Description: 订单写服务
 **/
@Api(value = "订单写服务", tags = "订单写服务")
@RestController
@RequestMapping(value = HarvestOmsPath.OrderPath.ORDER_WRITE_PATH)
public class OrderWriteController {

    @Autowired
    private OrderWriteClient orderWriteClient;

    @PostMapping("/build")
    public void build() {
        Long companyId = ContextHolder.getContext().getCompanyId();
        orderWriteClient.build(companyId);



    }

}
