package com.harvest.core.exception;

/**
 * @Author: Alodi
 * @Date: 2023/1/2 9:30 PM
 * @Description: 标准报错
 **/
public class StandardRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 5040811084038078844L;

    public static final int RESPONSE_STATUS_CODE = 580;

    private int code;

    private String stack;

    public StandardRuntimeException(String message) {
        super(message);
        this.code = RESPONSE_STATUS_CODE;
    }

    public StandardRuntimeException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }
}
