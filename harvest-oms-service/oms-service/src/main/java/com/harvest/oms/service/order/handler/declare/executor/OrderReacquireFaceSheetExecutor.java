package com.harvest.oms.service.order.handler.declare.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.oms.client.order.OrderDeclareClient;
import com.harvest.oms.client.order.OrderReadClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 11:54 PM
 * @Description: 重新获取面单执行器
 **/
@Component
public class OrderReacquireFaceSheetExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderReacquireFaceSheetExecutor.class);

    /**
     * 最多允许20个线程同时执行，防止并发量高时导致oom
     */
    private static final Semaphore SEMAPHORE = new Semaphore(20);

    /**
     * 获取面单异步线程池
     */
    private static final Executor REACQUIRE_FACE_SHEET_EXECUTOR = new ThreadPoolExecutor(25, 25, 2000, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(),
            new ThreadFactoryBuilder()
                    .setNameFormat("harvest-oms-reacquire-facesheet-%d")
                    .setUncaughtExceptionHandler((thread, e) -> LOGGER.error("ThreadPool:{} 发生异常", thread, e))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Autowired
    private OrderDeclareClient orderDeclareClient;

    @Autowired
    private OrderReadClient orderReadClient;

    /**
     * 重新获取面单
     *
     * @param companyId
     * @param orderId
     */
    public void reacquire(Long companyId, Long orderId) {
        OrderInfoDO order = orderReadClient.getOrderRich(companyId, orderId);
        this.check(companyId, order);
        this.asyncExecute(companyId, orderId);
    }

    private void asyncExecute(Long companyId, Long orderId) {
        REACQUIRE_FACE_SHEET_EXECUTOR.execute(() -> {
            try {
                SEMAPHORE.acquire();
                this.execute(companyId, orderId);
            } catch (Exception e) {
                LOGGER.error("OrderReacquireFaceSheetExecutor#reacquire#重新获取失败,companyId:{}, {}", companyId, orderId);
            } finally {
                SEMAPHORE.release();
            }
        });
    }

    /**
     * 开始获取面单
     *
     * @param companyId
     * @param orderId
     */
    private void execute(Long companyId, Long orderId) {
        // 判断面单信息是否存在&是否过期
        // 根据不同物流进行获取授权-获取运单号-获取面单-重新设置面单信息
    }

    private void check(Long companyId, OrderInfoDO order) {
        if (StringUtils.isEmpty(order.getDeliveryNo())) {
            throw new StandardRuntimeException(ExceptionCodes.OMS_MODULE_ERROR, "重新获取面单失败！请先申请交运～");
        }
    }

}
