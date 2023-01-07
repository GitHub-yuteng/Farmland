package com.harvest.core.feign.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * @Author: Alodi
 * @Date: 2023/1/6 3:09 PM
 * @Description: TODO
 **/
@Component
public class FeignErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(@NotNull ClientHttpResponse response) throws IOException {
        super.handleError(response);
    }
}
