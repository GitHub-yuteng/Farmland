package com.harvest.core.batch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2023/2/5 11:09 PM
 * @Description: TODO
 **/
@Data
public class BatchId implements Serializable {

    private static final long serialVersionUID = -5002420670654914779L;

    @ApiModelProperty("业务主键")
    private Long id;

    public String getKey() {
        return this.id.toString();
    }

}
