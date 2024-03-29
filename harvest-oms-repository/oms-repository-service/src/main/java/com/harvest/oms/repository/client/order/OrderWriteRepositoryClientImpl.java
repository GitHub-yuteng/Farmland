
package com.harvest.oms.repository.client.order;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.enums.oms.OrderStatusEnum;
import com.harvest.core.generator.IdGenerator;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.order.base.OrderRemark;
import com.harvest.oms.repository.domain.order.base.OrderWarehouse;
import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.domain.order.update.remark.OrderUpdateRemark;
import com.harvest.oms.repository.entity.FarmlandOmsOrderEntity;
import com.harvest.oms.repository.entity.FarmlandOmsOrderRemarkEntity;
import com.harvest.oms.repository.entity.FarmlandOmsOrderTagEntity;
import com.harvest.oms.repository.enums.tag.OrderTagTypeEnum;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderItemMapper;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderMapper;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderRemarkMapper;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderTagMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    private FarmlandOmsOrderRemarkMapper farmlandOmsOrderRemarkMapper;
    @Autowired
    private FarmlandOmsOrderTagMapper farmlandOmsOrderTagMapper;

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
        if (Objects.isNull(orderStatus)) {
            return;
        }
        FarmlandOmsOrderEntity entity = new FarmlandOmsOrderEntity();
        entity.setOrderStatus(orderStatus.getStatus());
        farmlandOmsOrderMapper.update(entity, new UpdateWrapper<FarmlandOmsOrderEntity>().lambda()
                .eq(FarmlandOmsOrderEntity::getId, orderId)
                .eq(FarmlandOmsOrderEntity::getCompanyId, companyId)
        );
    }

    @Override
    public void updateRemark(Long companyId, Long orderId, OrderUpdateRemark remark) {
        FarmlandOmsOrderRemarkEntity entity = new FarmlandOmsOrderRemarkEntity();

        OrderRemark.RemarkEnum remarkEnum = remark.getRemarkEnum();
        switch (remarkEnum) {
            case SELLER:
                if (StringUtils.isEmpty(remark.getSellerRemark())) {
                    return;
                }
                entity.setSellerRemark(remark.getSellerRemark());
                break;
            case BUYER:
                if (StringUtils.isEmpty(remark.getBuyerRemark())) {
                    return;
                }
                entity.setBuyerRemark(remark.getBuyerRemark());
                break;
            case SYSTEM:
                if (StringUtils.isEmpty(remark.getSystemRemark())) {
                    return;
                }
                entity.setSystemRemark(remark.getSystemRemark());
                break;
            case PRINT:
                if (StringUtils.isEmpty(remark.getPrintRemark())) {
                    return;
                }
                entity.setPrintRemark(remark.getPrintRemark());
                break;
            default:
                break;
        }

        farmlandOmsOrderRemarkMapper.update(entity, new UpdateWrapper<FarmlandOmsOrderRemarkEntity>().lambda()
                .eq(FarmlandOmsOrderRemarkEntity::getCompanyId, companyId)
                .eq(FarmlandOmsOrderRemarkEntity::getOrderId, orderId)
        );
    }

    @Override
    public void updateWarehouse(Long companyId, Long orderId, OrderWarehouse orderWarehouse) {
        FarmlandOmsOrderEntity entity = new FarmlandOmsOrderEntity();
        entity.setWarehouseId(orderWarehouse.getWarehouseId());
        entity.setWarehouseOwner(orderWarehouse.getWarehouseOwner().getType());
        farmlandOmsOrderMapper.update(entity, new UpdateWrapper<FarmlandOmsOrderEntity>().lambda()
                .eq(FarmlandOmsOrderEntity::getId, orderId)
                .eq(FarmlandOmsOrderEntity::getCompanyId, companyId)
        );
    }

    @Override
    public void abnormal(Long companyId, boolean abnormal, Long orderId) {
        FarmlandOmsOrderEntity entity = new FarmlandOmsOrderEntity();
        entity.setIsAbnormal(abnormal);
        farmlandOmsOrderMapper.update(entity, new UpdateWrapper<FarmlandOmsOrderEntity>().lambda()
                .eq(FarmlandOmsOrderEntity::getId, orderId)
                .eq(FarmlandOmsOrderEntity::getCompanyId, companyId)
        );
    }

    @Override
    public void tagRemoveAll(Long companyId, Long orderId) {
        farmlandOmsOrderTagMapper.delete(new UpdateWrapper<FarmlandOmsOrderTagEntity>().lambda()
                .eq(FarmlandOmsOrderTagEntity::getCompanyId, companyId)
                .eq(FarmlandOmsOrderTagEntity::getRecordId, orderId)
                .eq(FarmlandOmsOrderTagEntity::getRecordType, OrderTagTypeEnum.ORDER.getType())
        );
    }

    @Override
    public void tagRemove(Long companyId, Long orderId, List<Integer> tagValues) {
        if (CollectionUtils.isEmpty(tagValues)) {
            return;
        }
        farmlandOmsOrderTagMapper.delete(new UpdateWrapper<FarmlandOmsOrderTagEntity>().lambda()
                .eq(FarmlandOmsOrderTagEntity::getCompanyId, companyId)
                .eq(FarmlandOmsOrderTagEntity::getRecordId, orderId)
                .eq(FarmlandOmsOrderTagEntity::getRecordType, OrderTagTypeEnum.ORDER.getType())
                .in(FarmlandOmsOrderTagEntity::getTagValue, tagValues)
        );
    }
}
