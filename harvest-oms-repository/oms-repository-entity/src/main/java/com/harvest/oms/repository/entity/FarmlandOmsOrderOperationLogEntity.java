package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单操作记录
 * </p>
 *
 * @author Alodi
 * @since 2023-02-13 15:05:21
 */
@Getter
@Setter
@TableName("farmland_oms_order_operation_log")
@ApiModel(value = "FarmlandOmsOrderOperationLogEntity对象", description = "订单操作记录")
public class FarmlandOmsOrderOperationLogEntity {

    @TableId("order_id")
    private Long orderId;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("operation_type")
    private Integer operationType;

    @TableField("log")
    private String log;

    @TableField("operation_time")
    private LocalDateTime operationTime;
}
