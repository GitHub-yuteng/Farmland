package com.harvest.oms.service.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.harvest.core.context.SpringHelper;
import com.harvest.oms.domain.wms.WarehouseKey;
import com.harvest.wms.repository.client.warehouse.WarehouseReadClient;
import com.harvest.wms.repository.domain.warehouse.simple.WarehouseSimplePO;
import com.harvest.wms.repository.repository.domain.WarehouseDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Alodi
 * @Date: 2023/1/9 10:57 PM
 * @Description: 对于不经常更改的信息作为本地缓存 Loader
 **/
public class CacheLoader {

    private final static Logger LOGGER = LoggerFactory.getLogger(CacheLoader.class);

    private final static int DEFAULT_INITIAL_CAPACITY = 64;

    /**
     * 专供获取缓存的线程池
     */
    private final static Executor CACHE_LOADER_READ_EXECUTOR = new ThreadPoolExecutor(100, 100, 2000, TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(),
            new ThreadFactoryBuilder()
                    .setNameFormat("cache-loader-reading-%d")
                    .setUncaughtExceptionHandler((thread, e) -> LOGGER.error("CacheLoader-ThreadPool:{} 发生异常", thread, e))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 公司对应仓库缓存
     */
    public final static Cache<Long, Collection<WarehouseSimplePO>> COMPANY_ALL_WAREHOUSE_CACHE = Caffeine.newBuilder()
            .initialCapacity(DEFAULT_INITIAL_CAPACITY)
            .expireAfterWrite(12, TimeUnit.HOURS)
            .build(companyId -> SpringHelper.getBean(WarehouseReadClient.class).getByCompanyId(companyId));

    /**
     * 缓存仓库信息，只支持单个warehouseId查询缓存
     */
    public final static Cache<WarehouseKey, WarehouseDO> COMPANY_WAREHOUSE_CACHE = Caffeine.newBuilder()
            .initialCapacity(DEFAULT_INITIAL_CAPACITY)
            .expireAfterWrite(24, TimeUnit.HOURS)
            .build(warehouseKey -> SpringHelper.getBean(WarehouseReadClient.class).get(warehouseKey.getCompanyId(), warehouseKey.getWarehouseId()));

}
