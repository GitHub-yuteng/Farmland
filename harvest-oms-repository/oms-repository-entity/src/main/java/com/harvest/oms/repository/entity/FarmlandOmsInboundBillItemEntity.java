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
 * 
 * </p>
 *
 * @author Alodi
 * @since 2022-12-21 18:15:56
 */
@Getter
@Setter
@TableName("farmland_oms_inbound_bill_item")
@ApiModel(value = "FarmlandOmsInboundBillItemEntity对象", description = "")
public class FarmlandOmsInboundBillItemEntity {

    @TableId("id")
    private Long id;

    @ApiModelProperty("公司ID")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty("入库单ID")
    @TableField("bill_id")
    private Long billId;

    @TableField("record_item_id")
    private Long recordItemId;

    @TableField("spu_id")
    private Long spuId;

    @TableField("spu_name")
    private String spuName;

    @TableField("sku_id")
    private Long skuId;

    @TableField("sku_name")
    private String skuName;

    @TableField("is_package")
    private Boolean isPackage;

    @TableField("quantity")
    private BigDecimal quantity;

    @TableField("amount")
    private String amount;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
