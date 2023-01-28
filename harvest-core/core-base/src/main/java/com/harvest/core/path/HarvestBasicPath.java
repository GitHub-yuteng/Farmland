package com.harvest.core.path;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:16 PM
 * @Description: 订单路径定义
 **/
public interface HarvestBasicPath {

    String BASIC_PATH = "/harvest/basic";

    /**
     * 基础模块路径
     */
    interface BasicPath {

        String WEB_CONFIG = BASIC_PATH + "/webConfig";
    }

}
