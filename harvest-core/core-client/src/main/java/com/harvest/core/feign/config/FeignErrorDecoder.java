package com.harvest.core.feign.config;

import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.utils.JsonUtils;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Configuration
public class FeignErrorDecoder implements ErrorDecoder {

    private static final Default DECODER = new Default();

    @Override
    public Exception decode(final String methodKey, final Response response) {
        int status = response.status();

        Exception exception = null;
        try {
            if (status >= HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                String body = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
                Map<String, Object> content = JsonUtils.OBJECT_MAPPER.readValue(body, Map.class);
                String exceptionType = (String) content.get("exception");

                if (StandardRuntimeException.class.getSimpleName().equals(exceptionType)) {
                    exception = new StandardRuntimeException(
                            (Integer) content.get("error_code"),
                            (String) content.get("message")
                    );
                } else {
                    try {
                        Class<?> exceptionClass = Class.forName(exceptionType);
                        Constructor<?> constructor = exceptionClass.getConstructor(String.class);
                        exception = (Exception) constructor.newInstance(exceptionType);
                    } catch (Exception ex) {
                        exception = new RuntimeException((String) content.get("message"));
                    }
                }
            }
        } catch (Exception e) {
            // do nothing

        }
        if (exception == null) {
            exception = DECODER.decode(methodKey, response);
        }
        return exception;
    }
}