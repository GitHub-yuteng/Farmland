package com.harvest.oms.service.order.rich;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.domain.Page;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.repository.service.order.rich.OrderRichQueryRepositoryClient;
import com.harvest.oms.service.constants.HarvestOmsApplications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<OrderInfoDO> pageQueryOrderRich(Long companyId, PageOrderConditionQuery pageOrderConditionQuery) {
        Page<OrderSimplePO> orderSimplePage = orderRichQueryRepositoryClient.pageQueryOrderRich(companyId, pageOrderConditionQuery);
        Page<OrderInfoDO> result = this.convent(orderSimplePage);
        this.fillOrderItem(result);
        return result;
    }

    /**
     * 填充订单明细信息
     *
     * @param result
     */
    private void fillOrderItem(Page<OrderInfoDO> result) {

    }

    /**
     * 转换DO
     *
     * @param orderSimplePage
     * @return
     */
    private Page<OrderInfoDO> convent(Page<OrderSimplePO> orderSimplePage) {
        Collection<OrderSimplePO> orderSimpleList = orderSimplePage.getData();
        List<OrderInfoDO> orderInfoList = orderSimpleList.stream().map(orderSimple -> {
            OrderInfoDO orderInfoDO = new OrderInfoDO();
            BeanUtils.copyProperties(orderSimple, orderInfoDO);
            return orderInfoDO;
        }).collect(Collectors.toList());

        return new Page<>(
                orderSimplePage.getPageNo(),
                orderSimplePage.getPageSize(),
                orderInfoList,
                orderSimplePage.getCount()
        );

    }
}
