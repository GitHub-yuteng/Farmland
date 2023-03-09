package com.harvest.wms.repository.entity;

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
 * 仓库配置开关表
 * </p>
 *
 * @author Alodi
 * @since 2023-02-01 12:49:16
 */
@Getter
@Setter
@TableName("farmland_wms_warehouse_switch")
@ApiModel(value = "FarmlandWmsWarehouseSwitchEntity对象", description = "仓库配置开关表")
public class FarmlandWmsWarehouseSwitchEntity {

    @TableId("warehouse_id")
    private Long warehouseId;

    @ApiModelProperty("奇门对接")
    @TableField("open_qimen")
    private Boolean openQimen;

    @ApiModelProperty("自动同步库存")
    @TableField("open_auto_sync")
    private Boolean openAutoSync;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
