package com.harvest.oms.repository.mapper.order.read;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.repository.domain.logistics.OrderLogisticsKey;
import com.harvest.oms.repository.domain.logistics.simple.LogisticsChannelSimplePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/2/9 11:38 PM
 * @Description: TODO
 **/
@Mapper
public interface OrderLogisticsChannelReadMapper extends GlobalMacroDefinition {

    Collection<LogisticsChannelSimplePO> getChannelByLogisticsKeys(@Param(COMPANY_ID) Long companyId, @Param("list") List<OrderLogisticsKey> logisticsKeys);

}
