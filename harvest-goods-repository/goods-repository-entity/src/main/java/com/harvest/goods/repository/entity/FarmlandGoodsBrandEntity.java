package com.harvest.goods.repository.entity;

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
 * 
 * </p>
 *
 * @author Alodi
 * @since 2023-01-02 22:52:58
 */
@Getter
@Setter
@TableName("farmland_goods_brand")
@ApiModel(value = "FarmlandGoodsBrandEntity对象", description = "")
public class FarmlandGoodsBrandEntity {

    @TableId("id")
    private Long id;

    @ApiModelProperty("品牌名称")
    @TableField("brand_name")
    private String brandName;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
