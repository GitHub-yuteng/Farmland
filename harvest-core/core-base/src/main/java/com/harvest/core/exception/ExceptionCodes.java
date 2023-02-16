package com.harvest.core.exception;

/**
 * @Author: Alodi
 * @Date: 2023/1/31 9:55 AM
 * @Description: TODO
 **/
public interface ExceptionCodes {

    /**
     * 系统运行异常
     */
    int SYSTEM_EXCEPTION = -1;

    /**
     * 核心模块异常
     */
    int CORE_MODULE_ERROR = 1;

    /**
     * 通用异常
     */
    int BASE_MODULE_ERROR = 100;

    /**
     * OMS模块异常
     */
    int OMS_MODULE_ERROR = 1000;

}
