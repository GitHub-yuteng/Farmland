package com.harvest.goods.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-12-29 14:26:04
 */
@Getter
@Setter
@TableName("farmland_goods_package_relation")
@ApiModel(value = "FarmlandGoodsPackageRelationEntity对象", description = "")
public class FarmlandGoodsPackageRelationEntity {

    @TableId("id")
    private Long id;
}
