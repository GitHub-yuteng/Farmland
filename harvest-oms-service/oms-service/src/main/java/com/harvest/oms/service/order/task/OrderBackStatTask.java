package com.harvest.oms.service.order.task;

import com.harvest.core.constants.GlobalMacroDefinition;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 10:20 AM
 * @Description: 公司级别 后台轻任务（实时性较高的任务）
 **/
public interface OrderBackStatTask extends GlobalMacroDefinition {

    @AllArgsConstructor
    enum BackStatTaskEnum {

        /**
         * 后台任务枚举
         */
        STOCK_LACK          ("stock-lack-task"),
        LOGISTICS_TRACKING  ("logistics-tracking-task"),
        MERGE_TAG_MARKING   ("merge-tag-marking-task"),

        ;

        public final String taskName;

    }

    @ApiOperation("订单缺货打标")
    void StockLackStat(Long companyId);

    @ApiOperation("订单物流状态追踪")
    void LogisticsTrackingStat(Long companyId);

    @ApiOperation("订单合单打标")
    void OrderMergeTagMarkingStat(Long companyId);
}
