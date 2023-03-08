package com.harvest.oms.repository.client.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.declare.OrderDeclareSimplePO;
import com.harvest.oms.repository.domain.declare.OrderItemDeclareSimplePO;
import com.harvest.oms.repository.entity.FarmlandOmsOrderDeclarationEntity;
import com.harvest.oms.repository.entity.FarmlandOmsOrderDeclarationItemEntity;
import com.harvest.oms.repository.enums.declare.DeclareStatusEnum;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderDeclarationItemMapper;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderDeclarationMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (CollectionUtils.isEmpty(orderIds)) {
            return Collections.emptyList();
        }
        List<FarmlandOmsOrderDeclarationEntity> farmlandOmsOrderDeclarationEntities = farmlandOmsOrderDeclarationMapper.selectBatchIds(orderIds);
        if (CollectionUtils.isEmpty(farmlandOmsOrderDeclarationEntities)) {
            return Collections.emptyList();
        }

        List<FarmlandOmsOrderDeclarationItemEntity> farmlandOmsOrderDeclarationItemEntities = farmlandOmsOrderDeclarationItemMapper.selectList(new QueryWrapper<FarmlandOmsOrderDeclarationItemEntity>().lambda()
                .eq(FarmlandOmsOrderDeclarationItemEntity::getCompanyId, companyId)
                .eq(FarmlandOmsOrderDeclarationItemEntity::getOrderId, orderIds)
        );

        Map<Long, List<FarmlandOmsOrderDeclarationItemEntity>> declarationMap = farmlandOmsOrderDeclarationItemEntities.stream().collect(Collectors.groupingBy(FarmlandOmsOrderDeclarationItemEntity::getOrderId));

        return farmlandOmsOrderDeclarationEntities.stream().map(declaration -> {
            OrderDeclareSimplePO orderDeclareSimplePO = new OrderDeclareSimplePO();
            Long orderId = declaration.getOrderId();
            List<FarmlandOmsOrderDeclarationItemEntity> items = declarationMap.get(orderId);

            orderDeclareSimplePO.setOrderId(declaration.getOrderId());
            orderDeclareSimplePO.setCompanyId(declaration.getCompanyId());
            orderDeclareSimplePO.setStatus(declaration.getStatus());
            orderDeclareSimplePO.setLastResponse(declaration.getLastResponse());
            orderDeclareSimplePO.setItems(items.stream().map(item -> {
                OrderItemDeclareSimplePO orderItemDeclareSimplePO = new OrderItemDeclareSimplePO();
                orderItemDeclareSimplePO.setOrderItemId(item.getOrderItemId());
                orderItemDeclareSimplePO.setCompanyId(item.getCompanyId());
                orderItemDeclareSimplePO.setOrderId(item.getOrderId());
                orderItemDeclareSimplePO.setSpuId(item.getSpuId());
                orderItemDeclareSimplePO.setSkuId(item.getSkuId());
                orderItemDeclareSimplePO.setQuantity(item.getQuantity());
                return orderItemDeclareSimplePO;
            }).collect(Collectors.toList()));
            return orderDeclareSimplePO;
        }).collect(Collectors.toList());
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
    public void deleteDeclarationByOrderId(Long companyId, Long orderId) {
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
