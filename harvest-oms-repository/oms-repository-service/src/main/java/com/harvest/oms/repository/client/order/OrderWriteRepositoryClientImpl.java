
package com.harvest.oms.repository.client.order;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.enums.oms.OrderStatusEnum;
import com.harvest.core.generator.IdGenerator;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.entity.FarmlandOmsOrderEntity;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderItemMapper;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 8:03 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsRepositoryApplications.Path.ORDER_WRITE)
public class OrderWriteRepositoryClientImpl implements OrderWriteRepositoryClient {

    @Autowired
    private FarmlandOmsOrderMapper farmlandOmsOrderMapper;
    @Autowired
    private FarmlandOmsOrderItemMapper farmlandOmsOrderItemMapper;

    @Override
    public void insert(Long companyId, OrderSimplePO orderSimplePO) {
        FarmlandOmsOrderEntity farmlandOmsOrderEntity = new FarmlandOmsOrderEntity();
        farmlandOmsOrderEntity.setId(IdGenerator.generate());
        farmlandOmsOrderEntity.setCompanyId(companyId);
        farmlandOmsOrderEntity.setOrderNo(orderSimplePO.getOrderNo());
        farmlandOmsOrderEntity.setSourceType(orderSimplePO.getOrderSource().getType());
        farmlandOmsOrderEntity.setShopId(orderSimplePO.getShopId());
        farmlandOmsOrderEntity.setOrderStatus(orderSimplePO.getOrderStatus().getStatus());
        farmlandOmsOrderEntity.setAmount(JsonUtils.object2Json(orderSimplePO.getOrderAmount()));
//        farmlandOmsOrderEntity.setSpuKind();
//        farmlandOmsOrderEntity.setSkuKind();
//        farmlandOmsOrderEntity.setTotalQuantity();
        farmlandOmsOrderEntity.setCreatedTime(orderSimplePO.getOrderTime().getCreatedTime());
        farmlandOmsOrderEntity.setPaidTime(orderSimplePO.getOrderTime().getPaidTime());
        farmlandOmsOrderEntity.setSendTime(orderSimplePO.getOrderTime().getSendTime());
        farmlandOmsOrderEntity.setDeliveryDeadlineTime(orderSimplePO.getOrderTime().getDeliveryDeadlineTime());
        farmlandOmsOrderEntity.setModifyTime(orderSimplePO.getOrderTime().getModifyTime());
        farmlandOmsOrderEntity.setPrintTime(orderSimplePO.getOrderTime().getPrintTime());
//        farmlandOmsOrderEntity.setAuditManId();
        farmlandOmsOrderEntity.setAuditTime(orderSimplePO.getOrderTime().getAuditTime());
        farmlandOmsOrderEntity.setEndTime(orderSimplePO.getOrderTime().getEndTime());
//        farmlandOmsOrderEntity.setCreateType();
        farmlandOmsOrderEntity.setWarehouseOwner(orderSimplePO.getOrderWarehouse().getWarehouseOwner().getType());
        farmlandOmsOrderEntity.setWarehouseId(orderSimplePO.getOrderWarehouse().getWarehouseId());
//        farmlandOmsOrderEntity.setWaveNo();
        farmlandOmsOrderEntity.setLogisticType(orderSimplePO.getLogisticsEnum().getType());
        farmlandOmsOrderEntity.setLogisticsId(orderSimplePO.getLogisticsId());
        farmlandOmsOrderEntity.setChannelId(orderSimplePO.getChannelId());
        farmlandOmsOrderEntity.setDeliveryNo(orderSimplePO.getDeliveryNo());
//        farmlandOmsOrderEntity.setWeight();
//        farmlandOmsOrderEntity.setVolume();
//        farmlandOmsOrderEntity.setBuyerId();
//        farmlandOmsOrderEntity.setBusinessManId();
//        farmlandOmsOrderEntity.setWaitDeclare();
//        farmlandOmsOrderEntity.setLogisticsStatus();
//        farmlandOmsOrderEntity.setIgnoreOutbound();
//        farmlandOmsOrderEntity.setIsHangUp();
//        farmlandOmsOrderEntity.setHangUpCaseType();
//        farmlandOmsOrderEntity.setHangUpCaseId();
//        farmlandOmsOrderEntity.setEmptyOrder();
//        farmlandOmsOrderEntity.setIsAbnormal();
//        farmlandOmsOrderEntity.setIsDeleted();
        farmlandOmsOrderEntity.setRcTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setRmTime(LocalDateTime.now());
        farmlandOmsOrderMapper.insert(farmlandOmsOrderEntity);
    }

    @Override
    public void insertItems(Long companyId, Collection<OrderItemSimplePO> orderItemSimples) {

    }

    @Override
    public void updateDeclare(Long companyId, OrderSimplePO orderSimplePO) {
        FarmlandOmsOrderEntity entity = new FarmlandOmsOrderEntity();
        entity.setId(orderSimplePO.getOrderId());
        entity.setDeliveryNo(orderSimplePO.getDeliveryNo());
        entity.setWaitDeclare(orderSimplePO.getWaitDeclare());
        farmlandOmsOrderMapper.update(entity, new UpdateWrapper<FarmlandOmsOrderEntity>().lambda()
                .eq(FarmlandOmsOrderEntity::getId, orderSimplePO.getOrderId())
                .eq(FarmlandOmsOrderEntity::getCompanyId, companyId)
        );
    }

    @Override
    public void updateOrderStatus(Long companyId, Long orderId, OrderStatusEnum orderStatus) {
        FarmlandOmsOrderEntity entity = new FarmlandOmsOrderEntity();
        entity.setOrderStatus(orderStatus.getStatus());
        farmlandOmsOrderMapper.update(entity, new UpdateWrapper<FarmlandOmsOrderEntity>().lambda()
                .eq(FarmlandOmsOrderEntity::getId, orderId)
                .eq(FarmlandOmsOrderEntity::getCompanyId, companyId)
        );
    }
}
