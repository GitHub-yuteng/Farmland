package com.harvest.core.domain.file;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2023/1/8 12:34 AM
 * @Description: 项目文件
 **/
@Data
public class HarvestFile implements Serializable {

    private static final long serialVersionUID = 8022733001792279212L;

    private int type;

    private String url;
}
