package com.harvest.core.context;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Alodi
 * @Date: 2023/3/20 3:40 PM
 * @Description: TODO
 **/
public abstract class AbstractContext implements Context {

    private static final Map<String, Object> STORAGE = new HashMap<>();

    @Override
    public HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            return requestAttributes.getRequest();
        }
        return null;
    }

    @Override
    public void set(PreferenceName key, Object value) {

    }

    @Override
    public Long getCompanyId() {
        return null;
    }

    @Override
    public Long getUserId() {
        return null;
    }

    @Override
    public String getRequestId() {
        return null;
    }

    @Override
    public Long getAccountId() {
        return null;
    }

    @Override
    public int getApplicationType() {
        return 0;
    }

    @Override
    public Object getLoginAccount() {
        return null;
    }

    @Override
    public Object get(PreferenceName key) {
        return null;
    }
}
