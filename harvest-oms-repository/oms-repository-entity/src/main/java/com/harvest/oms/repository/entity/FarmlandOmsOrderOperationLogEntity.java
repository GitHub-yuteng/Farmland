package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 订单操作记录
 * </p>
 *
 * @author Alodi
 * @since 2023-03-16 11:38:37
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

    @TableField("content")
    private String content;

    @TableField("remark")
    private String remark;

    @TableField("internal")
    private Boolean internal;

    @TableField("user_id")
    private Long userId;

    @TableField("operation_time")
    private LocalDateTime operationTime;
}
