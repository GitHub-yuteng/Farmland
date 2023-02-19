package com.harvest.oms.repository.client.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.declare.OrderDeclareSimplePO;
import com.harvest.oms.repository.entity.FarmlandOmsOrderDeclarationEntity;
import com.harvest.oms.repository.entity.FarmlandOmsOrderDeclarationItemEntity;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderDeclarationItemMapper;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderDeclarationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 8:03 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsRepositoryApplications.Path.ORDER_DECLARE)
public class OrderDeclareRepositoryClientImpl implements OrderDeclareRepositoryClient {

    @Autowired
    private FarmlandOmsOrderDeclarationMapper farmlandOmsOrderDeclarationMapper;

    @Autowired
    private FarmlandOmsOrderDeclarationItemMapper farmlandOmsOrderDeclarationItemMapper;

    @Override
    public Collection<OrderDeclareSimplePO> listDeclaration(Long companyId, List<Long> orderIds) {
        return null;
    }

    @Override
    public void deleteDeclareByOrderId(Long companyId, Long orderId) {
        farmlandOmsOrderDeclarationMapper.delete(new QueryWrapper<FarmlandOmsOrderDeclarationEntity>().lambda()
                .eq(FarmlandOmsOrderDeclarationEntity::getOrderId, orderId)
        );

        farmlandOmsOrderDeclarationItemMapper.delete(new QueryWrapper<FarmlandOmsOrderDeclarationItemEntity>().lambda()
                .eq(FarmlandOmsOrderDeclarationItemEntity::getOrderId, orderId)
        );
    }

}
