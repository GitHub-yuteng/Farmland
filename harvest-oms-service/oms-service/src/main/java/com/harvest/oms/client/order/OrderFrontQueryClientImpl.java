package com.harvest.oms.client.order;

import com.harvest.core.domain.Page;
import com.harvest.core.annotation.feign.HarvestService;
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
 * @Description: 前端订单查询服务
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
        Collection<OrderInfoVO> data = orderConvertor.convertList(page.getData());
        this.triggerBackStatTask(companyId);
        return Page.build(page.getPageNo(), page.getPageSize(), data, page.getCount());
    }

    /**
     * 查询触发后台异步任务
     *
     * @param companyId
     */
    private void triggerBackStatTask(Long companyId) {
        taskExecutor.execute(() -> {
            orderBackStatTask.StockLackStat(companyId);
            orderBackStatTask.LogisticsTracking(companyId);
        });
    }

    @Override
    public OrderInfoVO frontQueryOrder(Long companyId, Long orderId) {
        OrderInfoDO order = orderRichQueryClient.getOrderRich(companyId, orderId);
        return orderConvertor.convert(order);
    }
}
