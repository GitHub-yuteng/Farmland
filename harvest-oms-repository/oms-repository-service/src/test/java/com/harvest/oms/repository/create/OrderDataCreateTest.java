package com.harvest.oms.repository.create;

import com.harvest.oms.repository.entity.FarmlandOmsOrderAddressEntity;
import com.harvest.oms.repository.entity.FarmlandOmsOrderEntity;
import com.harvest.oms.repository.entity.FarmlandOmsOrderItemEntity;
import com.harvest.oms.repository.entity.FarmlandOmsOrderRemarkEntity;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderAddressMapper;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderItemMapper;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderMapper;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderRemarkMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * @Author: Alodi
 * @Date: 2022/12/21 11:07 PM
 * @Description: TODO
 **/
@SpringBootTest
public class OrderDataCreateTest {

    @Autowired
    private FarmlandOmsOrderMapper farmlandOmsOrderMapper;
    @Autowired
    private FarmlandOmsOrderItemMapper farmlandOmsOrderItemMapper;
    @Autowired
    private FarmlandOmsOrderAddressMapper farmlandOmsOrderAddressMapper;
    @Autowired
    private FarmlandOmsOrderRemarkMapper farmlandOmsOrderRemarkMapper;

    @Test
    public void createOrder() {
        for (int i = 0; i < 1; i++) {

            FarmlandOmsOrderEntity farmlandOmsOrderEntity = this.buildOrder();
            farmlandOmsOrderMapper.insert(farmlandOmsOrderEntity);

            List<FarmlandOmsOrderItemEntity> items = this.buildOrderItem(farmlandOmsOrderEntity);
            items.forEach(farmlandOmsOrderItemMapper::insert);

            FarmlandOmsOrderAddressEntity farmlandOmsOrderAddressEntity = this.buildOrderAddress(farmlandOmsOrderEntity);
            farmlandOmsOrderAddressMapper.insert(farmlandOmsOrderAddressEntity);

            FarmlandOmsOrderRemarkEntity farmlandOmsOrderRemarkEntity = this.buildOrderRemark(farmlandOmsOrderEntity);
            farmlandOmsOrderRemarkMapper.insert(farmlandOmsOrderRemarkEntity);
        }
    }

    private FarmlandOmsOrderRemarkEntity buildOrderRemark(FarmlandOmsOrderEntity farmlandOmsOrderEntity) {
        FarmlandOmsOrderRemarkEntity farmlandOmsOrderRemarkEntity = new FarmlandOmsOrderRemarkEntity();
        farmlandOmsOrderRemarkEntity.setOrderId(farmlandOmsOrderEntity.getId());
        farmlandOmsOrderRemarkEntity.setCompanyId(farmlandOmsOrderEntity.getCompanyId());
        farmlandOmsOrderRemarkEntity.setSellerRemark("1");
        farmlandOmsOrderRemarkEntity.setBuyerRemark("2");
        farmlandOmsOrderRemarkEntity.setSystemRemark("3");
        farmlandOmsOrderRemarkEntity.setPrintRemark("4");
        farmlandOmsOrderRemarkEntity.setRcTime(LocalDateTime.now());
        farmlandOmsOrderRemarkEntity.setRmTime(LocalDateTime.now());
        return farmlandOmsOrderRemarkEntity;
    }

    private FarmlandOmsOrderAddressEntity buildOrderAddress(FarmlandOmsOrderEntity farmlandOmsOrderEntity) {
        FarmlandOmsOrderAddressEntity farmlandOmsOrderAddressEntity = new FarmlandOmsOrderAddressEntity();
        farmlandOmsOrderAddressEntity.setOrderId(farmlandOmsOrderEntity.getId());
        farmlandOmsOrderAddressEntity.setCompanyId(farmlandOmsOrderEntity.getCompanyId());
        farmlandOmsOrderAddressEntity.setAddressType(1);
        farmlandOmsOrderAddressEntity.setCountryCode("CN");
        farmlandOmsOrderAddressEntity.setCountryCn("中国");
        farmlandOmsOrderAddressEntity.setPostalCode("123");
        farmlandOmsOrderAddressEntity.setRegion1("浙江省");
        farmlandOmsOrderAddressEntity.setRegion2("杭州市");
        farmlandOmsOrderAddressEntity.setRegion3("西湖区");
        farmlandOmsOrderAddressEntity.setRegion4("蒋村街道");
        farmlandOmsOrderAddressEntity.setIsDeleted(false);
        farmlandOmsOrderAddressEntity.setRcTime(LocalDateTime.now());
        farmlandOmsOrderAddressEntity.setRmTime(LocalDateTime.now());
        return farmlandOmsOrderAddressEntity;
    }

    private List<FarmlandOmsOrderItemEntity> buildOrderItem(FarmlandOmsOrderEntity farmlandOmsOrderEntity) {
        List<FarmlandOmsOrderItemEntity> itemEntityList = Lists.newArrayList();
        for (int i = 0; i < 2; i++) {
            FarmlandOmsOrderItemEntity farmlandOmsOrderItemEntity = new FarmlandOmsOrderItemEntity();
            itemEntityList.add(farmlandOmsOrderItemEntity);
            farmlandOmsOrderItemEntity.setId(Math.abs(new Random().nextLong()));
            farmlandOmsOrderItemEntity.setCompanyId(farmlandOmsOrderEntity.getCompanyId());
            farmlandOmsOrderItemEntity.setShopId(farmlandOmsOrderEntity.getShopId());
            farmlandOmsOrderItemEntity.setOrderId(farmlandOmsOrderEntity.getId());
            farmlandOmsOrderItemEntity.setSourceType(farmlandOmsOrderEntity.getSourceType());
            farmlandOmsOrderItemEntity.setOrderItemStatus(10);
            farmlandOmsOrderItemEntity.setDeliveryNo("123213213");
            farmlandOmsOrderItemEntity.setSpuId(Math.abs(new Random().nextLong()));
            farmlandOmsOrderItemEntity.setSpuCode("123123");
            farmlandOmsOrderItemEntity.setSpuName("21321");
            farmlandOmsOrderItemEntity.setSkuId(Math.abs(new Random().nextLong()) + 1);
            farmlandOmsOrderItemEntity.setSkuCode("3232");
            farmlandOmsOrderItemEntity.setSkuName("123");
            farmlandOmsOrderItemEntity.setUnitPrice(new BigDecimal("3"));
            farmlandOmsOrderItemEntity.setDiscountedPrice(new BigDecimal("2"));
            farmlandOmsOrderItemEntity.setIsPackage(0);
            farmlandOmsOrderItemEntity.setQuantity(new BigDecimal("1"));
            farmlandOmsOrderItemEntity.setAmount("{}");
            farmlandOmsOrderItemEntity.setCreatedTime(LocalDateTime.now());
            farmlandOmsOrderItemEntity.setPaidTime(LocalDateTime.now());
            farmlandOmsOrderItemEntity.setSendTime(LocalDateTime.now());
            farmlandOmsOrderItemEntity.setEndTime(LocalDateTime.now());
            farmlandOmsOrderItemEntity.setModifyTime(LocalDateTime.now());
            farmlandOmsOrderItemEntity.setStockLack(new BigDecimal("1"));
            farmlandOmsOrderItemEntity.setStockLackTime(LocalDateTime.now());
            farmlandOmsOrderItemEntity.setPictureOssUrl("");
            farmlandOmsOrderItemEntity.setIsDeleted(false);
            farmlandOmsOrderItemEntity.setRcTime(LocalDateTime.now());
            farmlandOmsOrderItemEntity.setRmTime(LocalDateTime.now());
        }
        return itemEntityList;
    }

    private FarmlandOmsOrderEntity buildOrder() {
        long orderId = Math.abs(new Random().nextLong());
        FarmlandOmsOrderEntity farmlandOmsOrderEntity = new FarmlandOmsOrderEntity();
        farmlandOmsOrderEntity.setId(orderId);
        farmlandOmsOrderEntity.setCompanyId(Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setOrderNo("OD" + Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setSourceType(1);
        farmlandOmsOrderEntity.setShopId(Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setOrderStatus(10);
        farmlandOmsOrderEntity.setAmount("{}");
        farmlandOmsOrderEntity.setSpuKind(1);
        farmlandOmsOrderEntity.setSkuKind(1);
        farmlandOmsOrderEntity.setTotalQuantity(new BigDecimal("12"));
        farmlandOmsOrderEntity.setCreatedTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setPaidTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setSendTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setDeliveryDeadlineTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setModifyTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setPrintTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setAuditManId(0L);
        farmlandOmsOrderEntity.setAuditTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setEndTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setWarehouseOwner(1);
        farmlandOmsOrderEntity.setWarehouseId(Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setWaveNo("123");
        farmlandOmsOrderEntity.setLogisticType(1);
        farmlandOmsOrderEntity.setCarrierId(Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setChannelId(Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setDeliveryNo("123");
        farmlandOmsOrderEntity.setWeight(new BigDecimal("1"));
        farmlandOmsOrderEntity.setVolume(new BigDecimal("2"));
        farmlandOmsOrderEntity.setBuyerId(String.valueOf(Math.abs(new Random().nextLong())));
        farmlandOmsOrderEntity.setBusinessManId(Math.abs(new Random().nextLong()));
        farmlandOmsOrderEntity.setDeclareStatus(1);
        farmlandOmsOrderEntity.setIgnoreOutbound(false);
        farmlandOmsOrderEntity.setIsHangUp(false);
        farmlandOmsOrderEntity.setHangUpCaseType(1);
        farmlandOmsOrderEntity.setHangUpCaseId(Math.abs(new Random().nextInt()));
        farmlandOmsOrderEntity.setEmptyOrder(false);
        farmlandOmsOrderEntity.setIsAbnormal(false);
        farmlandOmsOrderEntity.setIsDeleted(false);
        farmlandOmsOrderEntity.setRcTime(LocalDateTime.now());
        farmlandOmsOrderEntity.setRmTime(LocalDateTime.now());
        return farmlandOmsOrderEntity;
    }

}
