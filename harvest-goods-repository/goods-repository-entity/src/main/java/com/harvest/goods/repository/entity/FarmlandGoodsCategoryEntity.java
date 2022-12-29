package com.harvest.goods.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-12-29 14:26:03
 */
@Getter
@Setter
@TableName("farmland_goods_category")
@ApiModel(value = "FarmlandGoodsCategoryEntity对象", description = "")
public class FarmlandGoodsCategoryEntity {

    @TableId("id")
    private Long id;

    @TableField("category_name")
    private String categoryName;

    @TableField("parent_id")
    private Long parentId;
}
