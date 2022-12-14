package com.harvest.core.config;

import com.google.common.collect.Maps;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Author: Alodi
 * @Date: 2023/1/6 1:22 PM
 * @Description: TODO
 **/
@ResponseBody
@ControllerAdvice(value = "com.harvest")
public class GlobalExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(StandardRuntimeException.class)
    public void standardRuntimeException(HttpServletRequest request, HttpServletResponse response, @NotNull Exception exception) throws IOException {
        StandardRuntimeException e = (StandardRuntimeException) exception;
        response.setStatus(StandardRuntimeException.RESPONSE_STATUS_CODE);
        ServletOutputStream os = response.getOutputStream();
        os.write(JsonUtils.object2Json(this.params(request, response, e)).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 组装错误数据
     *
     * @param request
     * @param response
     * @param exception
     * @return
     */
    private Map<String, Object> params(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        Map<String, Object> map = Maps.newHashMap();

        if (exception instanceof StandardRuntimeException) {
            StandardRuntimeException e = (StandardRuntimeException) exception;
            map.put("error_code", e.getCode());
            map.put("exception", e.getClass().getSimpleName());
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            map.put("message", StringUtils.defaultString(exception.getMessage(), exception.getClass().getSimpleName()));
        }

        map.put("message", exception.getMessage());
        map.put("path", request.getServletPath());
        map.put("status", response.getStatus());
        map.put("timestamp", System.currentTimeMillis());
        return map;
    }
}
