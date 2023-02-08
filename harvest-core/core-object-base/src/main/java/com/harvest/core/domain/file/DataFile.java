package com.harvest.core.domain.file;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2023/1/8 12:34 AM
 * @Description: 项目文件
 **/
@Data
public class DataFile implements Serializable {

    private static final long serialVersionUID = 8022733001792279212L;

    @ApiModelProperty("数据格式")
    private OuterDataFormat format;

    @ApiModelProperty("数据内容")
    private String data;

    @ApiModelProperty("远程文件地址")
    private String remoteFileUrl;

}
