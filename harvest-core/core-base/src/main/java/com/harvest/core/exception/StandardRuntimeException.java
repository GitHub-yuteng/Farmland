package com.harvest.core.exception;

/**
 * @Author: Alodi
 * @Date: 2023/1/2 9:30 PM
 * @Description: TODO
 **/
public class StandardRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 5040811084038078844L;

    public static final int RESPONSE_STATUS_CODE = 580;

    private int code;

    private String stack;

    public StandardRuntimeException(int code, String stack) {
        this.code = code;
        this.stack = stack;
    }

    public StandardRuntimeException(String message) {
        super(message);
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
