package com.harvest.oms.repository.create;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import com.harvest.oms.repository.entity.FarmlandOmsOrderEntity;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Alodi
 * @Date: 2022/12/21 11:07 PM
 * @Description: TODO
 **/
@SpringBootTest
public class OrderDataCreateTest {

    @Autowired
    private FarmlandOmsOrderMapper farmlandOmsOrderMapper;

    @Test
    public void createOrder() {
        for (int i = 0; i < 100; i++) {
            FarmlandOmsOrderEntity farmlandOmsOrderEntity = this.buildOrder();
            farmlandOmsOrderMapper.insert(farmlandOmsOrderEntity);
        }
    }

    @NotNull
    private FarmlandOmsOrderEntity buildOrder() {
        FarmlandOmsOrderEntity farmlandOmsOrderEntity = new FarmlandOmsOrderEntity();
        farmlandOmsOrderEntity.setId(Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setCompanyId(Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setOrderNo("OD" + Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setOrderSource(1);
        farmlandOmsOrderEntity.setShopId(Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setOrderStatus(10);
        farmlandOmsOrderEntity.setAmount("{}");
        farmlandOmsOrderEntity.setSpuKind(1);
        farmlandOmsOrderEntity.setSkuKind(1);
        farmlandOmsOrderEntity.setTotalQuantity(new BigDecimal("0"));
        farmlandOmsOrderEntity.setCreatedTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setPaidTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setSendTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setDeliveryDeadlineTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setModifyTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setPrintTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setAuditManId(0L);
        farmlandOmsOrderEntity.setAuditTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setEndTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setWarehouseOwner(0);
        farmlandOmsOrderEntity.setWarehouseId(Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setWaveNo(0);
        farmlandOmsOrderEntity.setLogisticType(0);
        farmlandOmsOrderEntity.setCarrierId(0L);
        farmlandOmsOrderEntity.setChannelId(0L);
        farmlandOmsOrderEntity.setDeliveryNo("");
        farmlandOmsOrderEntity.setWeight(new BigDecimal("1"));
        farmlandOmsOrderEntity.setVolume(new BigDecimal("2"));
        farmlandOmsOrderEntity.setBuyerId("");
        farmlandOmsOrderEntity.setBusinessManId(Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setDeclareStatus(0);
        farmlandOmsOrderEntity.setIgnoreOutbound(false);
        farmlandOmsOrderEntity.setIsHangUp(false);
        farmlandOmsOrderEntity.setHangUpCaseType(1);
        farmlandOmsOrderEntity.setHangUpCaseId(2);
        farmlandOmsOrderEntity.setEmptyOrder(false);
        farmlandOmsOrderEntity.setIsAbnormal(false);
        farmlandOmsOrderEntity.setIsDeleted(false);
        farmlandOmsOrderEntity.setRcTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setRmTime(LocalDateTime.now());
        return farmlandOmsOrderEntity;
    }

}
