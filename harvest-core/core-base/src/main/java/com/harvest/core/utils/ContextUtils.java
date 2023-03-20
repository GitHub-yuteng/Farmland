package com.harvest.core.utils;

import com.harvest.core.context.Context;
import com.harvest.core.context.ContextHolder;

/**
 * @Author: Alodi
 * @Date: 2023/3/20 3:28 PM
 * @Description: 上下文线程切换工具类
 **/
public class ContextUtils {

    public static void init(Long companyId) {
        Context context = ContextHolder.getContext();
        if (context == null) {
        }
    }
}
