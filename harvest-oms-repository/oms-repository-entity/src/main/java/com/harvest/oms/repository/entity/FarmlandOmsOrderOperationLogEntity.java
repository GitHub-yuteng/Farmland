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
 * @since 2023-03-16 12:02:04
 */
@Getter
@Setter
@TableName("farmland_oms_order_operation_log")
@ApiModel(value = "FarmlandOmsOrderOperationLogEntity对象", description = "订单操作记录")
public class FarmlandOmsOrderOperationLogEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("order_id")
    private Long orderId;

    @TableField("order_item_id")
    private Long orderItemId;

    @TableField("operation_type")
    private Integer operationType;

    @TableField("content")
    private String content;

    @TableField("remark")
    private String remark;

    @TableField("internal")
    private Boolean internal;

    @TableField("exception")
    private Boolean exception;

    @TableField("user_id")
    private Long userId;

    @TableField("operation_time")
    private LocalDateTime operationTime;
}
