package com.harvest.oms.repository.domain.order.pack;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:30 AM
 * @Description: TODO
 **/
@Data
public class OrderTag implements Serializable {

    private static final long serialVersionUID = -4601884852299102212L;

    private Long id;

    private Integer tagValue;

    private String simpleExtension;

}
