package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 物流渠道信息
 * </p>
 *
 * @author Alodi
 * @since 2023-01-29 14:52:41
 */
@Getter
@Setter
@TableName("farmland_oms_logistics_channel")
@ApiModel(value = "FarmlandOmsLogisticsChannelEntity对象", description = "物流渠道信息")
public class FarmlandOmsLogisticsChannelEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;
}
