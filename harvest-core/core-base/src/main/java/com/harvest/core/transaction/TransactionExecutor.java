package com.harvest.core.transaction;

import com.harvest.core.exception.StandardRuntimeException;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * @Author: Alodi
 * @Date: 2023/2/17 5:50 PM
 * @Description: 事务执行器
 **/
@Lazy
@Primary
@Component("transaction.executor")
public class TransactionExecutor {

    /**
     * 执行事务
     *
     * @param task 事务任务
     * @return 执行结果
     * @throws Exception
     */
    @TransactionalExec
    public <R> R execute(Callable<R> task) throws Exception {
        return exectask(task);
    }

    /**
     * 执行事务
     *
     * @param task 事务任务
     */
    @TransactionalExec
    public void execute(Runnable task) {
        exectask(task);
    }

    /**
     * 执行事务
     *
     * @param task 任务
     * @return 执行结果
     * @throws StandardRuntimeException
     */
    @TransactionalExec
    public <R> R execute(TransactionTask<R> task) throws StandardRuntimeException {
        return exectask(task);
    }

    /**
     * 执行任务
     *
     * @param task 任务
     */
    protected void exectask(Runnable task) {
        if (task != null) {
            task.run();
        }
    }

    /**
     * 执行任务
     *
     * @param task 任务
     * @return 执行结果
     * @throws Exception
     */
    protected <R> R exectask(Callable<R> task) throws Exception {
        return task == null ? null : task.call();
    }

    /**
     * 执行任务
     *
     * @param task 事务任务
     * @return 执行结果
     * @throws StandardRuntimeException
     */
    protected <R> R exectask(TransactionTask<R> task) throws StandardRuntimeException {
        return task == null ? null : task.execute();
    }

    /**
     * 事务执行任务
     *
     * @param <R> 结果类型
     * @author demon 2018-05-07
     */
    @FunctionalInterface
    public interface TransactionTask<R> {

        /**
         * 执行任务
         *
         * @return 执行结果
         */
        R execute() throws StandardRuntimeException;
    }

}
