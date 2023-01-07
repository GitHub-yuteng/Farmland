package com.harvest.oms.repository.enums.value;

import com.harvest.oms.repository.enums.OrderValueMapEnum;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2023/1/7 10:11 PM
 * @Description: TODO
 **/
public interface IOrderValueMap extends Serializable {

    /**
     * 值类型
     *
     * @return 类型
     */
    OrderValueMapEnum getType();

}
