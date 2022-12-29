package com.harvest.goods.repository.entity;

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
 * 商品规格信息
 * </p>
 *
 * @author Alodi
 * @since 2022-12-29 14:26:04
 */
@Getter
@Setter
@TableName("farmland_goods_sku")
@ApiModel(value = "FarmlandGoodsSkuEntity对象", description = "商品规格信息")
public class FarmlandGoodsSkuEntity {

    @TableId("id")
    private Long id;

    @TableField("spu_id")
    private Long spuId;

    @TableField("sku_code")
    private String skuCode;

    @TableField("sku_name")
    private String skuName;

    @TableField("barcode")
    private String barcode;

    @ApiModelProperty("采购价格/进货价价格")
    @TableField("purchase_price")
    private BigDecimal purchasePrice;

    @ApiModelProperty("采购周期")
    @TableField("procurement_cycle")
    private BigDecimal procurementCycle;

    @ApiModelProperty("生产周期")
    @TableField("production_cycle")
    private BigDecimal productionCycle;

    @TableField("volume")
    private BigDecimal volume;

    @TableField("max_volume")
    private BigDecimal maxVolume;

    @TableField("length")
    private BigDecimal length;

    @TableField("width")
    private BigDecimal width;

    @TableField("height")
    private BigDecimal height;

    @TableField("status")
    private Boolean status;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
