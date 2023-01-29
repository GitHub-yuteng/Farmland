package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 入库单表
 * </p>
 *
 * @author Alodi
 * @since 2023-01-29 14:52:40
 */
@Getter
@Setter
@TableName("farmland_oms_inbound_bill")
@ApiModel(value = "FarmlandOmsInboundBillEntity对象", description = "入库单表")
public class FarmlandOmsInboundBillEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;

    @ApiModelProperty("公司ID")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty("入库单号")
    @TableField("bill_no")
    private String billNo;

    @ApiModelProperty("店铺ID")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty("入库单类型")
    @TableField("bill_type")
    private Integer billType;

    @ApiModelProperty("记录号ID")
    @TableField("record_id")
    private Long recordId;

    @ApiModelProperty("记录号")
    @TableField("record_no")
    private String recordNo;

    @ApiModelProperty("金额大字段｜Json")
    @TableField("amount")
    private String amount;

    @ApiModelProperty("仓库ID")
    @TableField("warehouse_id")
    private Long warehouseId;

    @ApiModelProperty("运单号")
    @TableField("delivery_no")
    private String deliveryNo;

    @TableField("remark")
    private String remark;

    @ApiModelProperty("买家ID")
    @TableField("buyer_id")
    private Long buyerId;

    @ApiModelProperty("操作人ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("操作人姓名")
    @TableField("user_name")
    private String userName;

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
