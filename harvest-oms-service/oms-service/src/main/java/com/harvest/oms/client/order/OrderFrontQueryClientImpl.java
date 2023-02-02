package com.harvest.oms.client.order;

import com.harvest.core.domain.Page;
import com.harvest.core.feign.annotation.HarvestService;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.service.order.convertor.OrderConvertor;
import com.harvest.oms.service.order.task.OrderBackStatTask;
import com.harvest.oms.vo.order.OrderInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 4:37 PM
 * @Description: 订单丰富查询服务
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_FRONT)
public class OrderFrontQueryClientImpl implements OrderFrontQueryClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderFrontQueryClientImpl.class);

    @Autowired
    private OrderRichQueryClient orderRichQueryClient;

    @Autowired
    private OrderConvertor orderConvertor;

    @Autowired
    private OrderBackStatTask orderBackStatTask;

    @Autowired
    private AsyncTaskExecutor taskExecutor;

    @Override
    public Page<OrderInfoVO> frontPageQueryOrder(Long companyId, PageOrderConditionQuery condition) {
        Page<OrderInfoDO> page = orderRichQueryClient.pageQueryOrderRich(companyId, condition);
        Collection<OrderInfoVO> data = orderConvertor.convert(page.getData());
        this.triggerBackStatTask(companyId);
        return Page.build(page.getPageNo(), page.getPageSize(), data, page.getCount());
    }

    private void triggerBackStatTask(Long companyId) {
        taskExecutor.execute(() -> {
            orderBackStatTask.StockLackStat(companyId);
            orderBackStatTask.LogisticsTracking(companyId);
        });
    }
}
