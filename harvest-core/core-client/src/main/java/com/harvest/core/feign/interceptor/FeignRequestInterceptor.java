package com.harvest.core.feign.interceptor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: Alodi
 * @Date: 2023/1/6 10:52 AM
 * @Description: TODO
 **/
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (Request.HttpMethod.GET.name().equalsIgnoreCase(requestTemplate.method())) {
            // 此段处理时为了避免Feign为GET请求发送RequestBody，导致请求自动切换成POST模式
            requestTemplate.body();
        }
        try {
//            Map<String, String> headers = getHeaders();
            Map<String, String> headers = Maps.newHashMap();
            for (String headerName : headers.keySet()) {
                if (checkEssential(headerName)) {
                    requestTemplate.header(headerName, headers.get(headerName));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> getHeaders() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 请求头必要传递参数过滤,只携带必要请求头信息
     *
     * @param name
     * @return
     */
    private boolean checkEssential(String name) {
        ArrayList<String> headers = Lists.newArrayList("Authorization");
        return headers.stream().anyMatch(e -> e.equalsIgnoreCase(name));
    }
}
