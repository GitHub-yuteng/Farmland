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
 * 渠道地址信息
 * </p>
 *
 * @author Alodi
 * @since 2023-02-13 15:05:21
 */
@Getter
@Setter
@TableName("farmland_oms_logistics_channel_address")
@ApiModel(value = "FarmlandOmsLogisticsChannelAddressEntity对象", description = "渠道地址信息")
public class FarmlandOmsLogisticsChannelAddressEntity {

    @TableId("channel_id")
    private Long channelId;

    @ApiModelProperty("公司ID")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty("店铺ID")
    @TableField("SHOP_ID")
    private Long shopId;

    @ApiModelProperty("地址类型")
    @TableField("address_type")
    private Integer addressType;

    @ApiModelProperty("国家二字码")
    @TableField("country_code")
    private String countryCode;

    @ApiModelProperty("国家中文名称")
    @TableField("country_cn")
    private String countryCn;

    @ApiModelProperty("邮政编码")
    @TableField("postal_code")
    private String postalCode;

    @ApiModelProperty("省")
    @TableField("region1")
    private String region1;

    @ApiModelProperty("市")
    @TableField("region2")
    private String region2;

    @ApiModelProperty("区/县")
    @TableField("region3")
    private String region3;

    @ApiModelProperty("街道/乡村")
    @TableField("region4")
    private String region4;

    @ApiModelProperty("详细地址 region1 | region2 | region3 | region4 | address")
    @TableField("detail")
    private String detail;

    @ApiModelProperty("逻辑删除标记")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty("记录创建时间")
    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @ApiModelProperty("记录修改时间")
    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
