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
 * 商品配置开关表
 * </p>
 *
 * @author Alodi
 * @since 2023-01-02 22:52:59
 */
@Getter
@Setter
@TableName("farmland_goods_switch")
@ApiModel(value = "FarmlandGoodsSwitchEntity对象", description = "商品配置开关表")
public class FarmlandGoodsSwitchEntity {

    @ApiModelProperty("商品ID")
    @TableId("goods_id")
    private Long goodsId;

    @ApiModelProperty("批次管理")
    @TableField("open_batch")
    private Boolean openBatch;

    @ApiModelProperty("效期管理")
    @TableField("open_validity")
    private Boolean openValidity;

    @ApiModelProperty("记录创建时间")
    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @ApiModelProperty("记录修改时间")
    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
