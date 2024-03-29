package com.harvest.core.batch;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    protected Long id;

    @JsonIgnore
    public String getLockKey() {
        return this.id.toString();
    }

    public static BatchId build(Long id) {
        BatchId batchId = new BatchId();
        batchId.setId(id);
        return batchId;
    }

}
