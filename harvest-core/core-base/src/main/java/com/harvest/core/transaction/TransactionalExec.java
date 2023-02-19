package com.harvest.core.transaction;

import java.lang.annotation.*;

/**
 * @Author: Alodi
 * @Date: 2023/2/17 5:52 PM
 * @Description: TODO
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TransactionalExec {
}
