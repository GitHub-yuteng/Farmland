package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单明细表
 * </p>
 *
 * @author Alodi
 * @since 2022-12-18 21:08:52
 */
@Getter
@Setter
@TableName("farmland_oms_order_item")
@ApiModel(value = "FarmlandOmsOrderItemEntity对象", description = "订单明细表")
public class FarmlandOmsOrderItemEntity {

    @TableId("id")
    private Long id;

    @ApiModelProperty("公司id")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty("店铺id｜与订单保持一致")
    @TableField("shop_id")
    private Integer shopId;

    @ApiModelProperty("订单id")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty("订单来源｜与订单保持一致")
    @TableField("source_type")
    private Integer sourceType;

    @ApiModelProperty("订单明细状态")
    @TableField("order_item_status")
    private Integer orderItemStatus;

    @ApiModelProperty("运单号")
    @TableField("delivery_no")
    private String deliveryNo;

    @ApiModelProperty("商品id")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty("商品编码")
    @TableField("spu_code")
    private String spuCode;

    @ApiModelProperty("商品名称｜冗余")
    @TableField("spu_name")
    private String spuName;

    @ApiModelProperty("商品规格id")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty("商品规格编码")
    @TableField("sku_code")
    private String skuCode;

    @ApiModelProperty("商品规格名称｜冗余")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty("单价")
    @TableField("unit_price")
    private BigDecimal unitPrice;

    @ApiModelProperty("折扣单价")
    @TableField("discounted_price")
    private BigDecimal discountedPrice;

    @ApiModelProperty("是否组合商品")
    @TableField("is_package")
    private Integer isPackage;

    @ApiModelProperty("数量")
    @TableField("quantity")
    private BigDecimal quantity;

    @ApiModelProperty("金额大字段｜Json")
    @TableField("amount")
    private String amount;

    @ApiModelProperty("下单时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty("支付时间")
    @TableField("paid_time")
    private LocalDateTime paidTime;

    @ApiModelProperty("发货时间")
    @TableField("send_time")
    private LocalDateTime sendTime;

    @ApiModelProperty("订单完成或结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty("业务修改时间")
    @TableField("modify_time")
    private LocalDateTime modifyTime;

    @ApiModelProperty("缺货数量")
    @TableField("stock_lack")
    private BigDecimal stockLack;

    @ApiModelProperty("缺货更新时间")
    @TableField("stock_lack_time")
    private LocalDateTime stockLackTime;

    @ApiModelProperty("商品外部图片")
    @TableField("picture_oss_url")
    private String pictureOssUrl;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
