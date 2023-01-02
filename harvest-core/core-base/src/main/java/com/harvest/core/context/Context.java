package com.harvest.core.context;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Alodi
 * @Date: 2023/1/2 9:10 PM
 * @Description: TODO
 **/
public interface Context {

    HttpServletRequest getRequest();

    enum PreferenceName {

        /**
         * 当前线程上下文中存放的会话属性清单 当客户端调用Service时都出现在头信息中
         */
        companyID,
        userID,
        sessionID,
        accountID,
        remoteIP,
        requestID,
        transactionID,
        language,
        applicationType
    }

    Long getCompanyID();

    Long getUserID();

    Long getAccountID();

    int getApplicationType();

    void set(PreferenceName key, Object value);

    Object getLoginAccount();

}
