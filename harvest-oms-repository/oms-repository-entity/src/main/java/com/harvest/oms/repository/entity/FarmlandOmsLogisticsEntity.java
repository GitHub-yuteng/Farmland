package com.harvest.oms.repository.entity;

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
 * 物流信息
 * </p>
 *
 * @author Alodi
 * @since 2023-02-13 15:05:21
 */
@Getter
@Setter
@TableName("farmland_oms_logistics")
@ApiModel(value = "FarmlandOmsLogisticsEntity对象", description = "物流信息")
public class FarmlandOmsLogisticsEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("logistics_code")
    private String logisticsCode;

    @TableField("logistics")
    private String logistics;

    @ApiModelProperty("授权")
    @TableField("authorization")
    private String authorization;

    @TableField("status")
    private Byte status;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
