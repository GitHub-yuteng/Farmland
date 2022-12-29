package com.harvest.oms.repository.service.rich;

import com.google.common.collect.Maps;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.domain.Page;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.order.OrderInfoDO;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.mapper.order.OrderRichConditionQueryMapper;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.repository.service.order.rich.OrderRichQueryRepositoryClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 4:48 PM
 * @Description: TODO
 **/
@HarvestService(value = HarvestOmsRepositoryApplications.SERVICE_NAME, path = HarvestOmsRepositoryApplications.Path.ORDER_RICH)
public class OrderRichQueryRepositoryClientImpl implements OrderRichQueryRepositoryClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderRichQueryRepositoryClientImpl.class);

    private final static long TIME_OUT = 1000L * 5;

    @Autowired
    private OrderRichConditionQueryMapper orderRichConditionQueryMapper;

    @Override
    public Page<OrderSimplePO> pageQueryOrderRich(Long companyId, PageOrderConditionQuery condition) {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start("简化查询结构");
        Map<String, Object> paramsMap = this.conventParams(condition);
        stopWatch.stop();

        stopWatch.start("订单总数查询");
        Long count = orderRichConditionQueryMapper.countQueryOrderWithRichCondition(paramsMap);
        stopWatch.stop();

        Page<OrderSimplePO> page = new Page<>(condition.getPageNo(), condition.getPageSize());
        if (count == 0) {
            return page;
        }

        stopWatch.start("订单查询");
        Collection<OrderSimplePO> data = orderRichConditionQueryMapper.pageQueryOrderWithRichCondition(paramsMap);
        page.setData(data);
        stopWatch.stop();

        if (stopWatch.getTotalTimeMillis() > TIME_OUT) {
            LOGGER.warn(stopWatch.prettyPrint());
        }

        return page;
    }

    private Map<String, Object> conventParams(PageOrderConditionQuery condition) {
        Map<String, Object> paramsMap = Maps.newHashMap();

        if(StringUtils.isNotEmpty(condition.getOrderNo())){
            paramsMap.put("orderNo", condition.getOrderNo());
        }

        return paramsMap;
    }
}
