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
 * 订单 value map 扩展信息
 * </p>
 *
 * @author Alodi
 * @since 2022-12-18 21:08:53
 */
@Getter
@Setter
@TableName("farmland_oms_order_value_map")
@ApiModel(value = "FarmlandOmsOrderValueMapEntity对象", description = "订单 value map 扩展信息")
public class FarmlandOmsOrderValueMapEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty("value map 类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("定义值")
    @TableField("oms_key")
    private String omsKey;

    @ApiModelProperty("Json")
    @TableField("oms_value")
    private String omsValue;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
