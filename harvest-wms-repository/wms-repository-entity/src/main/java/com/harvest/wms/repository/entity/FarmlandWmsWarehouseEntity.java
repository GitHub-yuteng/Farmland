package com.harvest.wms.repository.entity;

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
 * 
 * </p>
 *
 * @author Alodi
 * @since 2023-01-15 16:55:09
 */
@Getter
@Setter
@TableName("farmland_wms_warehouse")
@ApiModel(value = "FarmlandWmsWarehouseEntity对象", description = "")
public class FarmlandWmsWarehouseEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("warehouse_name")
    private String warehouseName;

    @TableField("warehouse_code")
    private String warehouseCode;

    @TableField("warehouse_owner")
    private Integer warehouseOwner;

    @TableField("warehouse_type")
    private Integer warehouseType;

    @TableField("main_type")
    private Integer mainType;

    @TableField("is_delivery")
    private Boolean isDelivery;

    @TableField("status")
    private Integer status;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
