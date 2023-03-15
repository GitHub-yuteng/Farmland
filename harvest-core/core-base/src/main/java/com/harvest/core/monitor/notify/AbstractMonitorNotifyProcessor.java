package com.harvest.core.monitor.notify;

import com.harvest.core.monitor.domain.MonitorEventMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

/**
 * @Author: Alodi
 * @Date: 2023/3/15 3:09 PM
 * @Description: TODO
 **/
public abstract class AbstractMonitorNotifyProcessor {

    private final static RestTemplate REST_TEMPLATE = new RestTemplate();

    protected void sendMessage(String webhook, MonitorEventMessage message) {
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json;charset=UTF-8"));
        HttpEntity<MonitorEventMessage> httpEntity = new HttpEntity<>(message, headers);
        REST_TEMPLATE.postForEntity(webhook, httpEntity, Map.class);
    }

}
