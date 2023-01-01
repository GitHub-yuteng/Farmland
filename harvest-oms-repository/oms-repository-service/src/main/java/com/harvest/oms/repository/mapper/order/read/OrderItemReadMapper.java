package com.harvest.oms.repository.mapper.order.read;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 8:04 PM
 * @Description: TODO
 **/
@Mapper
public interface OrderItemReadMapper extends GlobalMacroDefinition {

    Collection<OrderItemSimplePO> listOrderItemByOrderIds(@Param("companyId") Long companyId, @Param("orderIds") List<Long> orderIds);

}
