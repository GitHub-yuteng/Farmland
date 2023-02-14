package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.domain.Page;
import com.harvest.core.service.redis.CacheService;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.redis.trigger.OrderTriggerKey;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.service.order.convertor.OrderConvertor;
import com.harvest.oms.service.order.task.OrderBackStatTask;
import com.harvest.oms.vo.order.OrderInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.util.StopWatch;

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

    @Autowired
    private CacheService cacheService;

    @Override
    public Page<OrderInfoVO> frontPageQueryOrder(Long companyId, PageOrderConditionQuery condition) {
        Page<OrderInfoDO> page = orderRichQueryClient.pageQueryOrderRich(companyId, condition);
        Collection<OrderInfoVO> data = orderConvertor.convertList(page.getData());
        this.triggerBackStatTask(companyId);
        return Page.build(page.getPageNo(), page.getPageSize(), data, page.getCount());
    }

    /**
     * 查询触发器后台异步任务
     *
     * @param companyId
     */
    private void triggerBackStatTask(Long companyId) {

        // 异步任务前置校验
        if (this.checkIntervalLimit(companyId)) {
            return;
        }

        cacheService.set(OrderTriggerKey.ORDER_QUERY_TRIGGER_KEY, companyId.toString(), System.currentTimeMillis());

        // 后台异步任务
        taskExecutor.execute(() -> {

            StopWatch stopWatch = new StopWatch("查询触发器后台异步任务");

            stopWatch.start("订单缺货打标");
            orderBackStatTask.StockLackStat(companyId);
            stopWatch.stop();

            stopWatch.start("订单物流状态追踪");
            orderBackStatTask.LogisticsTrackingStat(companyId);
            stopWatch.stop();

            stopWatch.start("订单合单打标");
            orderBackStatTask.OrderMergeTagMarkingStat(companyId);
            stopWatch.stop();

            LOGGER.info("OrderFrontQueryClientImpl#triggerBackStatTask#后台异步任务完成, 公司:{}\n{}", companyId, stopWatch.prettyPrint());

        });
    }

    private boolean checkIntervalLimit(Long companyId) {
        return cacheService.exists(OrderTriggerKey.ORDER_QUERY_TRIGGER_KEY, companyId.toString());
    }

    @Override
    public OrderInfoVO frontQueryOrder(Long companyId, Long orderId) {
        OrderInfoDO order = orderRichQueryClient.getOrderRich(companyId, orderId);
        return orderConvertor.convert(order);
    }
}
