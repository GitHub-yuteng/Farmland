package com.harvest.core.transaction;

import java.lang.annotation.*;

/**
 * @Author: Alodi
 * @Date: 2023/2/17 5:52 PM
 * @Description: TODO
 **/
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionalExec {

}
