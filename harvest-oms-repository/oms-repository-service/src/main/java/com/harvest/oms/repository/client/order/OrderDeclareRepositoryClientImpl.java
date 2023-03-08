package com.harvest.oms.repository.client.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.declare.OrderDeclareSimplePO;
import com.harvest.oms.repository.entity.FarmlandOmsOrderDeclarationEntity;
import com.harvest.oms.repository.entity.FarmlandOmsOrderDeclarationItemEntity;
import com.harvest.oms.repository.enums.declare.DeclareStatusEnum;
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
    public void draftDeclaration(Long companyId, OrderDeclareSimplePO orderDeclareSimple) {
        Long orderId = orderDeclareSimple.getOrderId();

        boolean exists = farmlandOmsOrderDeclarationMapper.exists(new QueryWrapper<FarmlandOmsOrderDeclarationEntity>().lambda()
                .eq(FarmlandOmsOrderDeclarationEntity::getCompanyId, companyId)
                .eq(FarmlandOmsOrderDeclarationEntity::getOrderId, orderId)
        );

        // 保存草稿交运信息
        FarmlandOmsOrderDeclarationEntity declarationEntity = new FarmlandOmsOrderDeclarationEntity();
        declarationEntity.setOrderId(orderId);
        declarationEntity.setCompanyId(companyId);
        declarationEntity.setStatus(DeclareStatusEnum.INIT.getKey());
        declarationEntity.setLastResponse("");
        farmlandOmsOrderDeclarationMapper.insert(declarationEntity);

        orderDeclareSimple.getItems().forEach(item -> {
            FarmlandOmsOrderDeclarationItemEntity declarationItemEntity = new FarmlandOmsOrderDeclarationItemEntity();
            declarationItemEntity.setOrderItemId(item.getOrderItemId());
            declarationItemEntity.setCompanyId(companyId);
            declarationItemEntity.setOrderId(item.getOrderId());
            declarationItemEntity.setSpuId(item.getSpuId());
            declarationItemEntity.setSkuId(item.getSkuId());
            declarationItemEntity.setQuantity(item.getQuantity());
            farmlandOmsOrderDeclarationItemMapper.insert(declarationItemEntity);
        });
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

    @Override
    public void setLastResponse(Long companyId, Long orderId, Object reponse) {
        FarmlandOmsOrderDeclarationEntity entity = new FarmlandOmsOrderDeclarationEntity();
        entity.setLastResponse(JsonUtils.object2Json(reponse));
        farmlandOmsOrderDeclarationMapper.update(entity, new UpdateWrapper<FarmlandOmsOrderDeclarationEntity>().lambda()
                .eq(FarmlandOmsOrderDeclarationEntity::getCompanyId, companyId)
                .eq(FarmlandOmsOrderDeclarationEntity::getOrderId, orderId)
        );
    }

}
