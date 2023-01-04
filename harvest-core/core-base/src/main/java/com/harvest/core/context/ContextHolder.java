package com.harvest.core.context;

import com.harvest.core.domain.CompanyId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/1/2 9:06 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ContextHolder extends CompanyId {

    private static final long serialVersionUID = -5962642042152648023L;

    private final static ThreadLocal<Context> CONTEXT = new ThreadLocal<>();

    public static Context getContext() {
        return CONTEXT.get();
    }

    public static void remove() {
        CONTEXT.remove();
    }
}
