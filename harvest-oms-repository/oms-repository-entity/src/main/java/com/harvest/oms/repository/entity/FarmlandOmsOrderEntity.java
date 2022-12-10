package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alodi
 * @since 2022-12-10 20:21:20
 */
@Getter
@Setter
@TableName("farmland_oms_order")
public class FarmlandOmsOrderEntity {

    @TableId("id")
    private Long id;

    /**
     * 公司id
     */
    @TableField("COMPANY_ID")
    private Long companyId;

    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 订单来源
     */
    @TableField("source_type")
    private Integer sourceType;

    /**
     * 店铺id
     */
    @TableField("shop_id")
    private Long shopId;

    /**
     * 订单状态
     */
    @TableField("ORDER_STATUS")
    private Integer orderStatus;

    /**
     * 金额大字段 Json
     */
    @TableField("amount")
    private String amount;

    /**
     * SPU种类数目
     */
    @TableField("spu_kind")
    private Integer spuKind;

    /**
     * SKU种类数目
     */
    @TableField("sku_kind")
    private Integer skuKind;

    /**
     * 商品总数
     */
    @TableField("total_quantity")
    private BigDecimal totalQuantity;

    /**
     * 下单时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 支付时间
     */
    @TableField("paid_time")
    private LocalDateTime paidTime;

    /**
     * 发货时间
     */
    @TableField("send_time")
    private LocalDateTime sendTime;

    /**
     * 发货截止时间
     */
    @TableField("delivery_deadline_time")
    private LocalDateTime deliveryDeadlineTime;

    /**
     * 业务修改时间
     */
    @TableField("modify_time")
    private LocalDateTime modifyTime;

    /**
     * 打单时间
     */
    @TableField("print_time")
    private LocalDateTime printTime;

    /**
     * 审核人
     */
    @TableField("audit_man_id")
    private Long auditManId;

    /**
     * 审核时间
     */
    @TableField("audit_time")
    private LocalDateTime auditTime;

    /**
     * 订单完成或结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 仓库类型
     */
    @TableField("warehouse_owner")
    private Integer warehouseOwner;

    /**
     * 仓库id
     */
    @TableField("warehouse_id")
    private Long warehouseId;

    /**
     * 波次号
     */
    @TableField("wave_no")
    private Integer waveNo;

    /**
     * 物流类型
     */
    @TableField("logistic_type")
    private Integer logisticType;

    /**
     * 承运商id
     */
    @TableField("carrier_id")
    private Long carrierId;

    /**
     * 渠道id
     */
    @TableField("channel_id")
    private Long channelId;

    /**
     * 运单号
     */
    @TableField("delivery_no")
    private String deliveryNo;

    /**
     * 订单总重量
     */
    @TableField("weight")
    private BigDecimal weight;

    /**
     * 订单总体积
     */
    @TableField("volume")
    private BigDecimal volume;

    /**
     * 买家id
     */
    @TableField("buyer_id")
    private String buyerId;

    /**
     * 业务员id
     */
    @TableField("business_man_id")
    private Long businessManId;

    /**
     * 申报状态
     */
    @TableField("declare_status")
    private Integer declareStatus;

    /**
     * 忽略生成出库单
     */
    @TableField("ignore_outbound")
    private Boolean ignoreOutbound;

    /**
     * 是否挂起
     */
    @TableField("is_hang_up")
    private Boolean isHangUp;

    /**
     * 挂起类型
     */
    @TableField("hang_up_case_type")
    private Integer hangUpCaseType;

    /**
     * 挂起原因id
     */
    @TableField("hang_up_case_id")
    private Integer hangUpCaseId;

    /**
     * 1:测评订单
     */
    @TableField("empty_order")
    private Boolean emptyOrder;

    /**
     * 异常订单
     */
    @TableField("is_abnormal")
    private Boolean isAbnormal;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
