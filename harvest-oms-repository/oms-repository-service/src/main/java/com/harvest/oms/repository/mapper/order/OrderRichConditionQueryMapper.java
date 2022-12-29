package com.harvest.oms.repository.mapper.order;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 8:04 PM
 * @Description: TODO
 **/
@Mapper
public interface OrderRichConditionQueryMapper extends GlobalMacroDefinition {

    /**
     * 订单丰富查询
     *
     * @param paramsMap
     * @return
     */
    Collection<OrderSimplePO> pageQueryOrderWithRichCondition(Map<String, Object> paramsMap);

    /**
     * 订单丰富查询总数
     *
     * @param paramsMap
     * @return
     */
    Long countQueryOrderWithRichCondition(Map<String, Object> paramsMap);
}
