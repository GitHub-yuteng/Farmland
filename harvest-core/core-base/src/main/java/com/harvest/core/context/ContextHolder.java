package com.harvest.core.context;

import com.harvest.core.domain.CompanyId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Alodi
 * @Date: 2023/1/2 9:06 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ContextHolder extends CompanyId {

    private static final long serialVersionUID = -5962642042152648023L;

    private static final ThreadLocal<Context> CONTEXT = new ThreadLocal<>();

    public static Context getContext() {

        Context context = CONTEXT.get();
        context = new Context() {
            @Override
            public HttpServletRequest getRequest() {
                return null;
            }

            @Override
            public void set(PreferenceName key, Object value) {

            }

            @Override
            public Long getCompanyId() {
                return 8510380986999420205L;
            }

            @Override
            public Long getUserId() {
                return 520L;
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
        };
        return context;
    }

    public static void remove() {
        CONTEXT.remove();
    }
}
