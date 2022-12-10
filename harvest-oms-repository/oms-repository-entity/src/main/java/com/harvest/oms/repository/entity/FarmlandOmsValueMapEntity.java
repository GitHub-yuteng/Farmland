package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alodi
 * @since 2022-12-10 20:21:21
 */
@Getter
@Setter
@TableName("farmland_oms_value_map")
public class FarmlandOmsValueMapEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("type")
    private Integer type;

    @TableField("oms_key")
    private String omsKey;

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
