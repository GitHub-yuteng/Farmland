package com.harvest.oms.repository.mapper.order;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.repository.domain.order.OrderInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 8:04 PM
 * @Description: TODO
 **/
@Mapper
public interface OrderRichQueryMapper extends GlobalMacroDefinition {

    OrderInfoDO pageQueryOrderWithRichCondition(@Param(COMPANY_ID) Long companyId, @Param("") Map<String, Object> params);

}
