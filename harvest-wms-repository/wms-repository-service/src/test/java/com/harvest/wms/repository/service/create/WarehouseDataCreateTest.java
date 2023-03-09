package com.harvest.wms.repository.service.create;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.harvest.core.enums.wms.WarehouseOwnerEnum;
import com.harvest.core.enums.wms.WarehouseTypeEnum;
import com.harvest.core.generator.IdGenerator;
import com.harvest.wms.repository.service.entity.FarmlandWmsWarehouseAddressEntity;
import com.harvest.wms.repository.service.entity.FarmlandWmsWarehouseEntity;
import com.harvest.wms.repository.mapper.FarmlandWmsWarehouseAddressMapper;
import com.harvest.wms.repository.mapper.FarmlandWmsWarehouseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author: Alodi
 * @Date: 2022/12/21 11:07 PM
 * @Description: TODO
 **/
@SpringBootTest
public class WarehouseDataCreateTest {

    private final static Executor EXECUTOR = new ThreadPoolExecutor(10, 10, 2000, TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("harvest-wms-create-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Autowired
    private FarmlandWmsWarehouseMapper farmlandWmsWarehouseMapper;
    @Autowired
    private FarmlandWmsWarehouseAddressMapper farmlandWmsWarehouseAddressMapper;

    @Test
    public void createWarehouse() throws InterruptedException {

        long companyId = 8510380986999420205L;
        long shopId = 8110380986999420205L;

        StopWatch stopWatch = new StopWatch("创建仓库信息");
        stopWatch.start("创建");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 1; i++) {
            EXECUTOR.execute(() -> {
                this.create(companyId);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("结束了！");
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

    }

    private void create(long companyId) {
        for (int i = 0; i < 10; i++) {
            long warehouseId = IdGenerator.generate();
            FarmlandWmsWarehouseEntity farmlandWmsWarehouseEntity = new FarmlandWmsWarehouseEntity();
            farmlandWmsWarehouseEntity.setId(warehouseId);
            farmlandWmsWarehouseEntity.setCompanyId(companyId);
            farmlandWmsWarehouseEntity.setWarehouseName("test" + i);
            farmlandWmsWarehouseEntity.setWarehouseCode(String.valueOf(Math.abs(new Random().nextLong())).substring(0, 8));
            farmlandWmsWarehouseEntity.setWarehouseOwner(WarehouseOwnerEnum.OWN.getKey());
            farmlandWmsWarehouseEntity.setWarehouseType(WarehouseTypeEnum.NORMAL.getType());
            farmlandWmsWarehouseEntity.setMainType(1);
            farmlandWmsWarehouseEntity.setIsDelivery(true);
            farmlandWmsWarehouseEntity.setStatus(1);
            farmlandWmsWarehouseEntity.setIsDeleted(false);
            farmlandWmsWarehouseEntity.setRcTime(LocalDateTime.now());
            farmlandWmsWarehouseEntity.setRmTime(LocalDateTime.now());
            farmlandWmsWarehouseMapper.insert(farmlandWmsWarehouseEntity);

            FarmlandWmsWarehouseAddressEntity farmlandWmsWarehouseAddressEntity = new FarmlandWmsWarehouseAddressEntity();
            farmlandWmsWarehouseAddressEntity.setWarehouseId(warehouseId);
            farmlandWmsWarehouseAddressEntity.setCompanyId(companyId);
            farmlandWmsWarehouseAddressEntity.setAddressType(1);
            farmlandWmsWarehouseAddressEntity.setCountryCode("CN");
            farmlandWmsWarehouseAddressEntity.setCountryCn("中国");
            farmlandWmsWarehouseAddressEntity.setPostalCode("123");
            farmlandWmsWarehouseAddressEntity.setRegion1("123");
            farmlandWmsWarehouseAddressEntity.setRegion2("123");
            farmlandWmsWarehouseAddressEntity.setRegion3("123");
            farmlandWmsWarehouseAddressEntity.setRegion4("123");
            farmlandWmsWarehouseAddressEntity.setDetail("123");
            farmlandWmsWarehouseAddressEntity.setIsDeleted(false);
            farmlandWmsWarehouseAddressEntity.setRcTime(LocalDateTime.now());
            farmlandWmsWarehouseAddressEntity.setRmTime(LocalDateTime.now());
            farmlandWmsWarehouseAddressMapper.insert(farmlandWmsWarehouseAddressEntity);
        }
    }

}
