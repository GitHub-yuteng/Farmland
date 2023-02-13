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
 * 
 * </p>
 *
 * @author Alodi
 * @since 2023-02-13 15:05:22
 */
@Getter
@Setter
@TableName("farmland_oms_snapshot")
@ApiModel(value = "FarmlandOmsSnapshotEntity对象", description = "")
public class FarmlandOmsSnapshotEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;

    @ApiModelProperty("公司ID")
    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("oms_key")
    private Long omsKey;

    @ApiModelProperty("快照类型")
    @TableField("snapshot_type")
    private Integer snapshotType;

    @ApiModelProperty("生成单据等对应快照")
    @TableField("big_value")
    private String bigValue;

    @ApiModelProperty("记录生成时间")
    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @ApiModelProperty("记录修改时间")
    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
