package com.harvest.rule.repository.entity;

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
 * 物流匹配规则表
 * </p>
 *
 * @author Alodi
 * @since 2023-02-22 11:50:48
 */
@Getter
@Setter
@TableName("farmland_rule_logistics_match")
@ApiModel(value = "FarmlandRuleLogisticsMatchEntity对象", description = "物流匹配规则表")
public class FarmlandRuleLogisticsMatchEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("rule_name")
    private String ruleName;

    @TableField("priority")
    private Integer priority;

    @TableField("logistics_id")
    private Long logisticsId;

    @TableField("channel_id")
    private Long channelId;

    @TableField("is_default")
    private Boolean isDefault;

    @TableField("status")
    private Boolean status;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
