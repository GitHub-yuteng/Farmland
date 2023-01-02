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
 * 组合商品关联关系表
 * </p>
 *
 * @author Alodi
 * @since 2023-01-02 22:52:58
 */
@Getter
@Setter
@TableName("farmland_goods_package_relation")
@ApiModel(value = "FarmlandGoodsPackageRelationEntity对象", description = "组合商品关联关系表")
public class FarmlandGoodsPackageRelationEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;

    @ApiModelProperty("商品ID")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty("规格ID")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty("组合商品ID")
    @TableField("pkg_spu_id")
    private Long pkgSpuId;

    @ApiModelProperty("组合商品规格ID")
    @TableField("pkg_sku_id")
    private Long pkgSkuId;

    @ApiModelProperty("数量")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty("是否启用")
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
