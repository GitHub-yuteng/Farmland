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
 * 出库单明细
 * </p>
 *
 * @author Alodi
 * @since 2022-12-21 18:15:57
 */
@Getter
@Setter
@TableName("farmland_oms_outbound_bill_item")
@ApiModel(value = "FarmlandOmsOutboundBillItemEntity对象", description = "出库单明细")
public class FarmlandOmsOutboundBillItemEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;

    @ApiModelProperty("公司ID")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty("出库单据ID")
    @TableField("bill_id")
    private Long billId;

    @ApiModelProperty("订单明细ID")
    @TableField("order_item_id")
    private Long orderItemId;

    @ApiModelProperty("商品ID")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty("商品名称")
    @TableField("spu_name")
    private String spuName;

    @ApiModelProperty("商品规格ID")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty("商品规格名称")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty("是否组合商品")
    @TableField("is_package")
    private Boolean isPackage;

    @ApiModelProperty("数量")
    @TableField("quantity")
    private BigDecimal quantity;

    @ApiModelProperty("金额大字段｜Json")
    @TableField("amount")
    private String amount;

    @ApiModelProperty("批次号列表")
    @TableField("batch_numbers")
    private String batchNumbers;

    @ApiModelProperty("快照ID")
    @TableField("snapshot_id")
    private Long snapshotId;

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
