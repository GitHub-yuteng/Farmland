package com.harvest.core.domain;

import com.harvest.core.enums.response.ResponseStatusEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 4934748935800590749L;
    private boolean success;
    private int status;
    private String message;
    private T data;

    public static ResponseResult<String> success() {
        ResponseResult<String> responseResult = new ResponseResult<>();
        responseResult.setSuccess(true);
        responseResult.setStatus(ResponseStatusEnum.SUCCESS.getCode());
        responseResult.setMessage(ResponseStatusEnum.SUCCESS.getDescribe());
        return responseResult;
    }

    public static <T> ResponseResult<T> success(String message) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(true);
        responseResult.setStatus(ResponseStatusEnum.SUCCESS.getCode());
        responseResult.setMessage(message);
        return responseResult;
    }

    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(true);
        responseResult.setStatus(ResponseStatusEnum.SUCCESS.getCode());
        responseResult.setMessage(ResponseStatusEnum.SUCCESS.getDescribe());
        responseResult.setData(data);
        return responseResult;
    }

    public static <T> ResponseResult<T> success(int code, String describe, T data) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(true);
        responseResult.setStatus(code);
        responseResult.setMessage(describe);
        responseResult.setData(data);
        return responseResult;
    }

    public static ResponseResult<String> fail() {
        ResponseResult<String> responseResult = new ResponseResult<>();
        responseResult.setSuccess(false);
        responseResult.setStatus(ResponseStatusEnum.FAIL.getCode());
        responseResult.setMessage(ResponseStatusEnum.FAIL.getDescribe());
        return responseResult;
    }

    public static <T> ResponseResult<T> fail(T data) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(false);
        responseResult.setStatus(ResponseStatusEnum.FAIL.getCode());
        responseResult.setMessage(ResponseStatusEnum.FAIL.getDescribe());
        responseResult.setData(data);
        return responseResult;
    }

    public static <T> ResponseResult<T> fail(String message) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(false);
        responseResult.setStatus(ResponseStatusEnum.FAIL.getCode());
        responseResult.setMessage(message);
        return responseResult;
    }

    public static <T> ResponseResult<T> fail(int code, String describe, T data) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(false);
        responseResult.setStatus(code);
        responseResult.setMessage(describe);
        responseResult.setData(data);
        return responseResult;
    }

}