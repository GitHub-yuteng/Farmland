package com.harvest.oms.repository.client.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.utils.Convertor;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import com.harvest.oms.repository.entity.FarmlandOmsOrderOperationLogEntity;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderOperationLogMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 8:03 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsRepositoryApplications.Path.ORDER_OPERATION_LOG)
public class OrderOperationLogRepositoryClientImpl implements OrderOperationLogRepositoryClient, Convertor<OrderOperationLog, FarmlandOmsOrderOperationLogEntity> {

    @Autowired
    private FarmlandOmsOrderOperationLogMapper farmlandOmsOrderOperationLogMapper;

    @Override
    public void batchStore(Collection<OrderOperationLog> logs) {
        if (CollectionUtils.isEmpty(logs)) {
            return;
        }
        this.convertList(logs).forEach(farmlandOmsOrderOperationLogMapper::insert);
    }

    /**
     * 单例转换
     *
     * @param source 待转换对象
     * @return 已转换对象
     */
    @Override
    public FarmlandOmsOrderOperationLogEntity convert(OrderOperationLog source) {
        FarmlandOmsOrderOperationLogEntity farmlandOmsOrderOperationLogEntity = new FarmlandOmsOrderOperationLogEntity();
        farmlandOmsOrderOperationLogEntity.setId(source.getId());
        farmlandOmsOrderOperationLogEntity.setOrderId(source.getBusinessId());
        farmlandOmsOrderOperationLogEntity.setCompanyId(source.getCompanyId());
        farmlandOmsOrderOperationLogEntity.setOperationType(source.getOperationType().getType());
        farmlandOmsOrderOperationLogEntity.setContent(source.getContent());
        farmlandOmsOrderOperationLogEntity.setRemark(source.getRemark());
        farmlandOmsOrderOperationLogEntity.setInternal(source.isInternal());
        farmlandOmsOrderOperationLogEntity.setUserId(source.getUserId());
        farmlandOmsOrderOperationLogEntity.setOperationTime(source.getLogTime());
        return farmlandOmsOrderOperationLogEntity;
    }
}
