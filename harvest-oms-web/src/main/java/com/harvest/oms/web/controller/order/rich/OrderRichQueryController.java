package com.harvest.oms.web.controller.order.rich;

import com.harvest.core.domain.Page;
import com.harvest.core.domain.ResponseResult;
import com.harvest.core.path.HarvestOmsPath;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.service.order.rich.OrderRichQueryClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:51 PM
 * @Description: 订单业务服务
 **/
@Api(value = "订单丰富查询", tags = "订单查询")
@RestController
@RequestMapping(value = HarvestOmsPath.OrderPath.OMS_RICH_PATH)
public class OrderRichQueryController {

    @Autowired
    private OrderRichQueryClient orderRichQueryClient;

    @PostMapping(value = "/page/query")
    public ResponseResult<Page<OrderInfoDO>> pageQueryOrderRich(@RequestBody PageOrderConditionQuery pageOrderConditionQuery) {
        Long companyId = 1L;
        Page<OrderInfoDO> result = orderRichQueryClient.pageQueryOrderRich(companyId, pageOrderConditionQuery);
        return ResponseResult.success(result);
    }

}
