package com.harvest.core.context;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Alodi
 * @Date: 2023/1/2 9:10 PM
 * @Description: 请求上下文
 **/
public interface Context {

    HttpServletRequest getRequest();

    enum PreferenceName {

        /**
         * 当前线程上下文中存放的会话属性清单 当客户端调用Service时都出现在头信息中
         */
        companyId,
        userId,
        sessionId,
        accountId,
        remoteIP,
        requestId,
        transactionId,
        language,
        applicationType
    }

    /**
     *
     * @param key
     * @param value
     */
    void set(PreferenceName key, Object value);

    Long getCompanyId();

    Long getUserId();

    Long getAccountId();

    int getApplicationType();

    Object getLoginAccount();

}
