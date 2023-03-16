package com.harvest.inventory.repository.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 库存
 * </p>
 *
 * @author Alodi
 * @since 2023-03-16 21:20:20
 */
@Getter
@Setter
@TableName("farmland_inventory")
@ApiModel(value = "FarmlandInventoryEntity对象", description = "库存")
public class FarmlandInventoryEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("warehouse_id")
    private Long warehouseId;

    @TableField("spu_id")
    private Long spuId;

    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty("实际库存")
    @TableField("actual_quantity")
    private BigDecimal actualQuantity;

    @ApiModelProperty("锁定库存")
    @TableField("lock_quantity")
    private BigDecimal lockQuantity;

    @ApiModelProperty("在途库存")
    @TableField("underway_quantity")
    private BigDecimal underwayQuantity;

    @ApiModelProperty("安全库存")
    @TableField("safety_quantity")
    private BigDecimal safetyQuantity;

    @ApiModelProperty("预警下限")
    @TableField("warn_up")
    private BigDecimal warnUp;

    @ApiModelProperty("预警上限")
    @TableField("warn_low")
    private BigDecimal warnLow;

    @ApiModelProperty("商品成本")
    @TableField("goods_cost")
    private BigDecimal goodsCost;

    @ApiModelProperty("库存成本")
    @TableField("warehouse_cost")
    private BigDecimal warehouseCost;

    @ApiModelProperty("次品实际库存")
    @TableField("actual_quantity_defect")
    private BigDecimal actualQuantityDefect;

    @ApiModelProperty("次品锁定库存")
    @TableField("lock_quantity_defect")
    private BigDecimal lockQuantityDefect;

    @ApiModelProperty("次品在途库存")
    @TableField("underway_quantity_defect")
    private BigDecimal underwayQuantityDefect;

    @TableField("status")
    private Integer status;

    @TableField("if_deleted")
    private Boolean ifDeleted;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
