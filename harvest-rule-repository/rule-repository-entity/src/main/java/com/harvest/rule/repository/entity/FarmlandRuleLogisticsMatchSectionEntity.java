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
 * 
 * </p>
 *
 * @author Alodi
 * @since 2023-02-22 11:50:48
 */
@Getter
@Setter
@TableName("farmland_rule_logistics_match_section")
@ApiModel(value = "FarmlandRuleLogisticsMatchSectionEntity对象", description = "")
public class FarmlandRuleLogisticsMatchSectionEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("rule_id")
    private Long ruleId;

    @TableField("section_type")
    private Integer sectionType;

    @TableField("rule_content")
    private String ruleContent;

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
