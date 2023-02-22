
package com.harvest.oms.repository.client.order;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.entity.FarmlandOmsOrderEntity;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 8:03 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsRepositoryApplications.Path.ORDER_WRITE)
public class OrderWriteRepositoryClientImpl implements OrderWriteRepositoryClient {

    @Autowired
    private FarmlandOmsOrderMapper farmlandOmsOrderMapper;

    @Override
    public void insert(Long companyId, OrderSimplePO orderSimplePO) {

    }

    @Override
    public void updateDeclare(Long companyId, FarmlandOmsOrderEntity entity) {
        farmlandOmsOrderMapper.update(entity, new UpdateWrapper<FarmlandOmsOrderEntity>().lambda()
                .eq(FarmlandOmsOrderEntity::getId, entity.getId())
                .eq(FarmlandOmsOrderEntity::getCompanyId, companyId)
        );
    }
}
