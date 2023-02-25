package com.harvest.oms.repository.client.logistics;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.core.utils.Convertor;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.logistics.OrderLogisticsKey;
import com.harvest.oms.repository.domain.logistics.simple.LogisticsChannelSimplePO;
import com.harvest.oms.repository.domain.logistics.simple.LogisticsSimplePO;
import com.harvest.oms.repository.entity.FarmlandOmsLogisticsChannelAddressEntity;
import com.harvest.oms.repository.entity.FarmlandOmsLogisticsEntity;
import com.harvest.oms.repository.mapper.FarmlandOmsLogisticsChannelAddressMapper;
import com.harvest.oms.repository.mapper.FarmlandOmsLogisticsMapper;
import com.harvest.oms.repository.mapper.order.read.OrderLogisticsChannelReadMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:13 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsRepositoryApplications.LogisticsPath.LOGISTICS_READ)
public class LogisticsReadRepositoryClientImpl implements LogisticsReadRepositoryClient, Convertor<FarmlandOmsLogisticsEntity, LogisticsSimplePO> {

    @Autowired
    private OrderLogisticsChannelReadMapper orderLogisticsChannelReadMapper;

    @Autowired
    private FarmlandOmsLogisticsMapper farmlandOmsLogisticsMapper;

    @Autowired
    private FarmlandOmsLogisticsChannelAddressMapper farmlandOmsLogisticsChannelAddressMapper;

    @Override
    public Collection<LogisticsChannelSimplePO> getChannelByLogisticsKeys(Long companyId, List<OrderLogisticsKey> logisticsKeys) {
        if (CollectionUtils.isEmpty(logisticsKeys)) {
            return Collections.emptyList();
        }
        return orderLogisticsChannelReadMapper.getChannelByLogisticsKeys(companyId, logisticsKeys);
    }

    @Override
    public List<FarmlandOmsLogisticsChannelAddressEntity> getChannelAddress(Long companyId, Long channelId) {
        List<FarmlandOmsLogisticsChannelAddressEntity> entities = farmlandOmsLogisticsChannelAddressMapper.selectList(new QueryWrapper<FarmlandOmsLogisticsChannelAddressEntity>().lambda()
                .eq(FarmlandOmsLogisticsChannelAddressEntity::getCompanyId, companyId)
                .eq(FarmlandOmsLogisticsChannelAddressEntity::getChannelId, channelId)
        );
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return entities;
    }

    @Override
    public LogisticsSimplePO getLogistics(Long companyId, LogisticsEnum logisticsType) {
        FarmlandOmsLogisticsEntity entity = farmlandOmsLogisticsMapper.selectOne(new QueryWrapper<FarmlandOmsLogisticsEntity>().lambda()
                .eq(FarmlandOmsLogisticsEntity::getCompanyId, companyId)
                .eq(FarmlandOmsLogisticsEntity::getLogisticsCode, logisticsType.getCode())
        );
        return this.convert(entity);
    }

    /**
     * 单例转换
     *
     * @param source 待转换对象
     * @return 已转换对象
     */
    @Override
    public LogisticsSimplePO convert(FarmlandOmsLogisticsEntity source) {
        LogisticsSimplePO logisticsSimple = new LogisticsSimplePO();
        logisticsSimple.setAuthorization(source.getAuthorization());
        logisticsSimple.setLogisticsId(source.getId());
        logisticsSimple.setLogisticsCode(source.getLogisticsCode());
        logisticsSimple.setLogistics(source.getLogistics());
        logisticsSimple.setStatus(source.getStatus());
        logisticsSimple.setCompanyId(source.getCompanyId());
        return logisticsSimple;
    }
}
