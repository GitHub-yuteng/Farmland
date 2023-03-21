package com.harvest.core.exception;

/**
 * @Author: Alodi
 * @Date: 2023/3/21 1:40 PM
 * @Description: TODO
 **/
public class RateLimitRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -2320421196120605200L;

    public static final int RESPONSE_STATUS_CODE = 580;

    private int code;

    public RateLimitRuntimeException(String message) {
        super(message);
        this.code = RESPONSE_STATUS_CODE;
    }

    public RateLimitRuntimeException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
