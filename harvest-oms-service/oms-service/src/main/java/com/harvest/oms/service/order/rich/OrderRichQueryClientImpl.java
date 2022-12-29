package com.harvest.oms.service.order.rich;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.domain.Page;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.repository.service.order.rich.OrderRichQueryRepositoryClient;
import com.harvest.oms.service.constants.HarvestOmsApplications;
import com.harvest.oms.vo.order.OrderInfoVO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 4:37 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_RICH)
public class OrderRichQueryClientImpl implements OrderRichQueryClient {

    @Autowired
    private OrderRichQueryRepositoryClient orderRichQueryRepositoryClient;

    @Override
    public Page<OrderInfoVO> pageQueryOrderRich(Long companyId, PageOrderConditionQuery pageOrderConditionQuery) {
        Page<OrderSimplePO> orderSimplePOPage = orderRichQueryRepositoryClient.pageQueryOrderRich(companyId, pageOrderConditionQuery);
        System.out.println(JsonUtils.object2Json(orderSimplePOPage));
        // 处理查询业务
        return this.convent(orderSimplePOPage);
    }

    private Page<OrderInfoVO> convent(Page<OrderSimplePO> orderSimplePOPage) {
        return new Page<>(orderSimplePOPage.getPageNo(), orderSimplePOPage.getPageSize());
    }
}
