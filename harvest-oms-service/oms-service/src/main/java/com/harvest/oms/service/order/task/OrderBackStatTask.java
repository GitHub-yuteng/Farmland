package com.harvest.oms.service.order.task;

import com.harvest.core.constants.GlobalMacroDefinition;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 10:20 AM
 * @Description: 后台轻任务（实时性较高的任务）
 **/
public interface OrderBackStatTask extends GlobalMacroDefinition {

    @AllArgsConstructor
    enum BackStatTaskEnum {

        /**
         * 后台任务枚举
         */
        STOCK_LACK          ("stock-lack-task"),
        LOGISTICS_TRACKING  ("logistics-tracking-task"),

        ;

        public final String taskName;

    }

    @ApiOperation("订单缺货统计")
    void StockLackStat(long companyId);

    @ApiOperation("订单物流状态追踪")
    void LogisticsTracking(long companyId);

}
