package com.harvest.core.domain.address;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:22 PM
 * @Description: TODO
 **/
@Data
public class HarvestAddress implements Serializable {

    private static final long serialVersionUID = -7028877982659423747L;

    @ApiModelProperty("地址类型")
    private Integer addressType;

    @ApiModelProperty("国家二字码")
    private String countryCode;

    @ApiModelProperty("国家中文名称")
    private String countryCn;

    @ApiModelProperty("邮政编码")
    private String postalCode;

    @ApiModelProperty(value = "一级行政区划，比如中国的省", notes = "非中国地区，不要求模型标准")
    private String region1;

    @ApiModelProperty(value = "二级行政区划，比如中国的地级市", notes = "非中国地区，不要求模型标准")
    private String region2;

    @ApiModelProperty(value = "三级行政区划，比如中国的区/县", notes = "非中国地区，不要求模型标准")
    private String region3;

    @ApiModelProperty(value = "四级行政区划，比如中国的街道/乡村", notes = "非中国地区，不要求模型标准")
    private String region4;

    /**
     * 判断基础地址是否有变更
     *
     * @param harvestAddress
     * @return
     */
    public boolean isChange(HarvestAddress harvestAddress) {
        return harvestAddress.getRegion1().equals(this.getRegion1()) &&
                harvestAddress.getRegion2().equals(this.getRegion2()) &&
                harvestAddress.getRegion3().equals(this.getRegion3()) &&
                harvestAddress.getRegion4().equals(this.getRegion4());
    }

}
