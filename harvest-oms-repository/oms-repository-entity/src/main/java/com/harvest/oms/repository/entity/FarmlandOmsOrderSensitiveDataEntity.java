package com.harvest.oms.repository.entity;

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
 * 订单敏感信息数据表
 * </p>
 *
 * @author Alodi
 * @since 2022-12-11 18:57:05
 */
@Getter
@Setter
@TableName("farmland_oms_order_sensitive_data")
@ApiModel(value = "FarmlandOmsOrderSensitiveDataEntity对象", description = "订单敏感信息数据表")
public class FarmlandOmsOrderSensitiveDataEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("order_id")
    private Long orderId;

    @TableField("data_type")
    private Integer dataType;

    @TableField("encrypt_method")
    private Integer encryptMethod;

    @TableField("data_text")
    private String dataText;

    @TableField("fuzzy_text")
    private String fuzzyText;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
