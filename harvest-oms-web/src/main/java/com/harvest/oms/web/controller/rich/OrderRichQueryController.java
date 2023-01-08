package com.harvest.oms.web.controller.rich;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.context.Context;
import com.harvest.core.context.ContextHolder;
import com.harvest.core.domain.Page;
import com.harvest.core.domain.ResponseResult;
import com.harvest.core.path.HarvestOmsPath;
import com.harvest.oms.client.order.rich.OrderRichQueryClient;
import com.harvest.oms.domain.order.OrderItemDO;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.vo.order.OrderInfoVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:51 PM
 * @Description: 订单业务服务
 **/
@Api(value = "订单丰富查询", tags = "订单查询")
@RestController
@RequestMapping(value = HarvestOmsPath.OrderPath.OMS_RICH_PATH)
public class OrderRichQueryController implements GlobalMacroDefinition {

    @Autowired
    private OrderRichQueryClient orderRichQueryClient;

    @PostMapping(value = "/page/query")
    public ResponseResult<Page<OrderInfoVO>> pageQueryOrderRich(@RequestBody PageOrderConditionQuery condition) {
        Context context = ContextHolder.getContext();
        Long companyId = 8510380986999420205L;
        Page<OrderInfoVO> result = orderRichQueryClient.pageQueryOrderRich(companyId, condition);
        return ResponseResult.success(result);
    }

    @PostMapping(value = "/items")
    public ResponseResult<Collection<OrderItemDO>> queryOrderItemsRich(@RequestParam(OMS.ORDER_ID) Long orderId) {
        Context context = ContextHolder.getContext();
        Long companyId = 8510380986999420205L;
        Collection<OrderItemDO> items = orderRichQueryClient.queryOrderItemsRich(companyId, orderId);
        return ResponseResult.success(items);
    }

}
