package com.harvest.core.feign.config;

import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.utils.JsonUtils;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Configuration
public class FeignErrorDecoder implements ErrorDecoder {

    private final static Default DECODER = new Default();

    @Override
    public Exception decode(final String methodKey, final Response response) {
        int status = response.status();

        Exception exception = null;
        try {
            if (status >= HttpStatus.SC_INTERNAL_SERVER_ERROR) {

                String body = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
                Map<String, Object> errorContent = JsonUtils.OBJECT_MAPPER.readValue(body, Map.class);

                exception = new StandardRuntimeException("测试feign");
            }
        } catch (IOException e) {
            // do nothing

        }
        if (exception == null) {
            exception = DECODER.decode(methodKey, response);
        }
        return exception;
    }
}