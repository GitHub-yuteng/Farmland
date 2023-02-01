package com.harvest.oms.repository.domain.logistics;

import com.harvest.core.domain.CompanyId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 4:01 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderLogisticsKey extends CompanyId {

    private static final long serialVersionUID = 3584012581034612621L;

    @ApiModelProperty("承运商Id")
    private Long carrierId;
    @ApiModelProperty("渠道Id")
    private Long channelId;

}
