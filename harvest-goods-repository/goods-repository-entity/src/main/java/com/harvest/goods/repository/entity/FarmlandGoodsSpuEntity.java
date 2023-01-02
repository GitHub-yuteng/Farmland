package com.harvest.goods.repository.entity;

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
 * 商品信息
 * </p>
 *
 * @author Alodi
 * @since 2023-01-02 22:52:59
 */
@Getter
@Setter
@TableName("farmland_goods_spu")
@ApiModel(value = "FarmlandGoodsSpuEntity对象", description = "商品信息")
public class FarmlandGoodsSpuEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;

    @ApiModelProperty("公司ID")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty("商品规格编码")
    @TableField("GOODS_CODE")
    private String goodsCode;

    @ApiModelProperty("商品名称")
    @TableField("goods_name")
    private String goodsName;

    @ApiModelProperty("分类ID")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty("品牌ID")
    @TableField("brand_id")
    private Long brandId;

    @ApiModelProperty("单位ID")
    @TableField("unit_id")
    private Long unitId;

    @ApiModelProperty("货号/产品编号")
    @TableField("product_no")
    private String productNo;

    @ApiModelProperty("1:单规格商品 | 2:多规格商品")
    @TableField("goods_type")
    private Integer goodsType;

    @ApiModelProperty("1:普通商品 | 2:组合商品")
    @TableField("is_package")
    private Boolean isPackage;

    @ApiModelProperty("长度单位")
    @TableField("length_unit")
    private Integer lengthUnit;

    @ApiModelProperty("宽度单位")
    @TableField("weight_unit")
    private Integer weightUnit;

    @ApiModelProperty("体积单位")
    @TableField("volume_unit")
    private Integer volumeUnit;

    @ApiModelProperty("保质期")
    @TableField("quality_period")
    private Integer qualityPeriod;

    @ApiModelProperty("禁收期")
    @TableField("forbid_receive_period")
    private Integer forbidReceivePeriod;

    @ApiModelProperty("禁售期")
    @TableField("forbid_sale_period")
    private Integer forbidSalePeriod;

    @ApiModelProperty("允收期")
    @TableField("allow_accept_period")
    private Integer allowAcceptPeriod;

    @ApiModelProperty("生命周期(天)")
    @TableField("life_cycle")
    private Integer lifeCycle;

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
