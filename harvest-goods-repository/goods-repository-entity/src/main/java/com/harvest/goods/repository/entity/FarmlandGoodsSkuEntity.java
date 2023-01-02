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
 * @since 2023-01-02 22:52:59
 */
@Getter
@Setter
@TableName("farmland_goods_sku")
@ApiModel(value = "FarmlandGoodsSkuEntity对象", description = "商品规格信息")
public class FarmlandGoodsSkuEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;

    @ApiModelProperty("公司ID")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty("商品ID")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty("商品规格编码")
    @TableField("sku_code")
    private String skuCode;

    @ApiModelProperty("商品规格名称")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty("条码")
    @TableField("barcode")
    private String barcode;

    @ApiModelProperty("采购价格/进货价")
    @TableField("purchase_price")
    private BigDecimal purchasePrice;

    @ApiModelProperty("批发价")
    @TableField("wholesale_price")
    private BigDecimal wholesalePrice;

    @ApiModelProperty("标准售价")
    @TableField("sale_price")
    private BigDecimal salePrice;

    @ApiModelProperty("吊牌价")
    @TableField("tag_price")
    private BigDecimal tagPrice;

    @ApiModelProperty("采购周期")
    @TableField("procurement_cycle")
    private BigDecimal procurementCycle;

    @ApiModelProperty("生产周期")
    @TableField("production_cycle")
    private BigDecimal productionCycle;

    @ApiModelProperty("体积")
    @TableField("volume")
    private BigDecimal volume;

    @ApiModelProperty("最大体积")
    @TableField("max_volume")
    private BigDecimal maxVolume;

    @ApiModelProperty("长度")
    @TableField("length")
    private BigDecimal length;

    @ApiModelProperty("宽度")
    @TableField("width")
    private BigDecimal width;

    @ApiModelProperty("高度")
    @TableField("height")
    private BigDecimal height;

    @ApiModelProperty("生产成本")
    @TableField("production_cost")
    private BigDecimal productionCost;

    @ApiModelProperty("关键字")
    @TableField("key_word")
    private String keyWord;

    @ApiModelProperty("状态")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty("逻辑删除标志")
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
