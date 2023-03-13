package com.harvest.core.service.biz;

import com.harvest.core.log.OperationLog;

/**
 * @Author: Alodi
 * @Date: 2023/3/13 2:44 PM
 * @Description: TODO
 **/
public interface BizOperationLog<T extends OperationLog> {


    /**
     * 匹配
     */
    Class<T> match();

    /**
     * 保存
     */
    void store(OperationLog log);

}
