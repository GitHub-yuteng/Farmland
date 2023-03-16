package com.harvest.inventory.repository.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
 *  库存流水
 * </p>
 *
 * @author Alodi
 * @since 2023-03-16 21:20:21
 */
@Getter
@Setter
@TableName("farmland_inventory_stream")
@ApiModel(value = "FarmlandInventoryStreamEntity对象", description = " 库存流水")
public class FarmlandInventoryStreamEntity {

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

    @ApiModelProperty("单据类型")
    @TableField("bill_type")
    private Integer billType;

    @TableField("bill_id")
    private Long billId;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
