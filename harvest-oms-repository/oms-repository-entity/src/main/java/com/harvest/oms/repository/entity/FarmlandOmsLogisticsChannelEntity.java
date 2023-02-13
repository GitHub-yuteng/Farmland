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
 * 物流渠道信息
 * </p>
 *
 * @author Alodi
 * @since 2023-02-13 15:05:21
 */
@Getter
@Setter
@TableName("farmland_oms_logistics_channel")
@ApiModel(value = "FarmlandOmsLogisticsChannelEntity对象", description = "物流渠道信息")
public class FarmlandOmsLogisticsChannelEntity {

    @ApiModelProperty("primary key")
    @TableId("channel_id")
    private Long channelId;

    @ApiModelProperty("公司ID")
    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("logistics_id")
    private Long logisticsId;

    @ApiModelProperty("渠道编码")
    @TableField("channel_code")
    private String channelCode;

    @ApiModelProperty("渠道名字")
    @TableField("channel_name")
    private String channelName;

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
