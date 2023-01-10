package com.harvest.oms.service.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.harvest.core.context.SpringHelper;
import com.harvest.oms.domain.wms.WarehouseKey;
import com.harvest.wms.repository.client.warehouse.WarehouseReadClient;
import com.harvest.wms.repository.domain.WarehouseDO;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Alodi
 * @Date: 2023/1/9 10:57 PM
 * @Description: TODO
 **/
public class CacheLoader {

    /**
     * 公司对应仓库缓存
     */
    public final static Cache<Long, Collection<WarehouseDO>> COMPANY_ALL_WAREHOUSE_CACHE = Caffeine.newBuilder()
            .expireAfterWrite(12, TimeUnit.HOURS)
            .build(companyId -> SpringHelper.getBean(WarehouseReadClient.class).getByCompanyId(companyId));

    /**
     * 缓存仓库信息，只支持单个warehouseId查询缓存
     */
    public final static Cache<WarehouseKey, WarehouseDO> COMPANY_WAREHOUSE_CACHE = Caffeine.newBuilder()
            .expireAfterWrite(12, TimeUnit.HOURS)
            .build(warehouseKey -> SpringHelper.getBean(WarehouseReadClient.class).get(warehouseKey.getCompanyId(), warehouseKey.getWarehouseId()));

}
