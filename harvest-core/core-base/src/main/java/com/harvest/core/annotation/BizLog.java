package com.harvest.core.annotation;

import java.lang.annotation.*;

/**
 * @Author: Alodi
 * @Date: 2023/3/13 2:34 PM
 * @Description: 业务日志注解 配合 BizLogUtils 使用
 * @link {@link com.harvest.core.service.utils.BizLogUtils}
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BizLog {
}
