package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Alodi
 * @since 2023-02-14 09:33:30
 */
@Getter
@Setter
@TableName("farmland_oms_order")
@ApiModel(value = "FarmlandOmsOrderEntity对象", description = "订单表")
public class FarmlandOmsOrderEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;

    @ApiModelProperty("公司id")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty("订单号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("订单来源")
    @TableField("source_type")
    private Integer sourceType;

    @ApiModelProperty("店铺id")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty("订单状态")
    @TableField("ORDER_STATUS")
    private Integer orderStatus;

    @ApiModelProperty("金额大字段 Json")
    @TableField("amount")
    private String amount;

    @ApiModelProperty("SPU种类数目")
    @TableField("spu_kind")
    private Integer spuKind;

    @ApiModelProperty("SKU种类数目")
    @TableField("sku_kind")
    private Integer skuKind;

    @ApiModelProperty("商品总数")
    @TableField("total_quantity")
    private BigDecimal totalQuantity;

    @ApiModelProperty("下单时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty("支付时间")
    @TableField("paid_time")
    private LocalDateTime paidTime;

    @ApiModelProperty("发货时间")
    @TableField("send_time")
    private LocalDateTime sendTime;

    @ApiModelProperty("发货截止时间")
    @TableField("delivery_deadline_time")
    private LocalDateTime deliveryDeadlineTime;

    @ApiModelProperty("业务修改时间")
    @TableField("modify_time")
    private LocalDateTime modifyTime;

    @ApiModelProperty("打单时间")
    @TableField("print_time")
    private LocalDateTime printTime;

    @ApiModelProperty("审核人")
    @TableField("audit_man_id")
    private Long auditManId;

    @ApiModelProperty("审核时间")
    @TableField("audit_time")
    private LocalDateTime auditTime;

    @ApiModelProperty("订单完成或结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty("0:正常下单;1:手动创建")
    @TableField("create_type")
    private Integer createType;

    @ApiModelProperty("仓库类型")
    @TableField("warehouse_owner")
    private Integer warehouseOwner;

    @ApiModelProperty("仓库id")
    @TableField("warehouse_id")
    private Long warehouseId;

    @ApiModelProperty("波次号")
    @TableField("wave_no")
    private String waveNo;

    @ApiModelProperty("物流类型")
    @TableField("logistic_type")
    private Integer logisticType;

    @ApiModelProperty("承运商id")
    @TableField("logistics_id")
    private Long logisticsId;

    @ApiModelProperty("渠道id")
    @TableField("channel_id")
    private Long channelId;

    @ApiModelProperty("运单号")
    @TableField("delivery_no")
    private String deliveryNo;

    @ApiModelProperty("订单总重量")
    @TableField("weight")
    private BigDecimal weight;

    @ApiModelProperty("订单总体积")
    @TableField("volume")
    private BigDecimal volume;

    @ApiModelProperty("买家id")
    @TableField("buyer_id")
    private String buyerId;

    @ApiModelProperty("业务员id")
    @TableField("business_man_id")
    private Long businessManId;

    @ApiModelProperty("待申报")
    @TableField("wait_declare")
    private Boolean waitDeclare;

    @ApiModelProperty("物流状态")
    @TableField("logistics_status")
    private Integer logisticsStatus;

    @ApiModelProperty("忽略生成出库单")
    @TableField("ignore_outbound")
    private Boolean ignoreOutbound;

    @ApiModelProperty("是否挂起")
    @TableField("is_hang_up")
    private Boolean isHangUp;

    @ApiModelProperty("挂起类型")
    @TableField("hang_up_case_type")
    private Integer hangUpCaseType;

    @ApiModelProperty("挂起原因id")
    @TableField("hang_up_case_id")
    private Integer hangUpCaseId;

    @ApiModelProperty("1:测评订单")
    @TableField("empty_order")
    private Boolean emptyOrder;

    @ApiModelProperty("异常订单")
    @TableField("is_abnormal")
    private Boolean isAbnormal;

    @ApiModelProperty("逻辑删除标记")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty("记录创建时间")
    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @ApiModelProperty("记录修改时间")
    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
