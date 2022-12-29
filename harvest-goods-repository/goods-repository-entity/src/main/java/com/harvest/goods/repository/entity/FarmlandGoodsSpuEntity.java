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
 * @since 2022-12-29 14:26:04
 */
@Getter
@Setter
@TableName("farmland_goods_spu")
@ApiModel(value = "FarmlandGoodsSpuEntity对象", description = "商品信息")
public class FarmlandGoodsSpuEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("GOODS_CODE")
    private String goodsCode;

    @TableField("goods_name")
    private String goodsName;

    @TableField("category_id")
    private Long categoryId;

    @TableField("brand_id")
    private Long brandId;

    @ApiModelProperty("货号/产品编号")
    @TableField("product_no")
    private String productNo;

    @ApiModelProperty("0: 普通商品 1: 组合商品")
    @TableField("is_package")
    private Boolean isPackage;

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
