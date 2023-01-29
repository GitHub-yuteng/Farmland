package com.harvest.core.monitor.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Alodi
 * @Date: 2023/1/29 5:20 PM
 * @Description: TODO
 **/
@Data
public class ExceptionRecord {

    /**
     * 公司id
     */
    private long companyId;

    /**
     * 异常信息
     */
    private String cause;

    /**
     * 发生时间
     */
    private Date happenTime;

}
