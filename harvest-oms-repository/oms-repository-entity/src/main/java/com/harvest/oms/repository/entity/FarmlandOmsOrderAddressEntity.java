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
 * 订单地址信息 ｜ 地址信息只有特定环节会变更，大部分信息流转是固定的，因此单独分拆独立表；部分平台履约场景，可能获取不到地址信息；
 * </p>
 *
 * @author Alodi
 * @since 2022-12-10 20:21:20
 */
@Getter
@Setter
@TableName("farmland_oms_order_address")
public class FarmlandOmsOrderAddressEntity {

    @TableId("id")
    private Long id;

    /**
     * 公司id
     */
    @TableField("COMPANY_ID")
    private Long companyId;

    /**
     * 地址类型
     */
    @TableField("address_type")
    private Integer addressType;

    /**
     * 国家二字码
     */
    @TableField("country_code")
    private String countryCode;

    /**
     * 国家中文名称
     */
    @TableField("country_cn")
    private String countryCn;

    /**
     * 邮政编码
     */
    @TableField("postal_code")
    private String postalCode;

    /**
     * 省
     */
    @TableField("region1")
    private String region1;

    /**
     * 市
     */
    @TableField("region2")
    private String region2;

    /**
     * 区/县
     */
    @TableField("region3")
    private String region3;

    /**
     * 街道/乡村
     */
    @TableField("region4")
    private String region4;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
