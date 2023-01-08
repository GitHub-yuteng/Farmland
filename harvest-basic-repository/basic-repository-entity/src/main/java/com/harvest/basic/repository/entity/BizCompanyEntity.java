package com.harvest.basic.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-01-08 23:11:03
 */
@Getter
@Setter
@TableName("biz_company")
@ApiModel(value = "BizCompanyEntity对象", description = "")
public class BizCompanyEntity {

    @TableId("id")
    private Long id;
}
