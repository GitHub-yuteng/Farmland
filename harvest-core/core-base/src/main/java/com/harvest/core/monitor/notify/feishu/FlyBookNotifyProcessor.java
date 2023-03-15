package com.harvest.core.monitor.notify.feishu;

import com.harvest.core.monitor.domain.MonitorEventMessage;
import com.harvest.core.monitor.notify.MonitorNotifyProcessor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @Author: Alodi
 * @Date: 2023/3/15 2:43 PM
 * @Description: TODO
 **/
@Component
public class FlyBookNotifyProcessor implements MonitorNotifyProcessor {

    private static final String WEB_HOOK = "https://open.feishu.cn/open-apis/bot/v2/hook/6c0f90a1-44cc-4802-8bac-1538e9685e3d";

    private final static RestTemplate REST_TEMPLATE = new RestTemplate();

    private final static String MARKDOWN_TEMPLATE = " <font color=\"#dd0000\" size=\"4\"> %s </font><br />\n" +
            "   > - **【事件级别】:** %s\n" +
            "   > - **【监控类型】:** %s\n" +
            "   > - **【公司】:** %s\n" +
            "   > - **【请求id】:** %s\n" +
            "   > - **【耗时】:** %s\n" +
            "   > - **【发生时间】:** %s\n" +
            "   > - **【节点信息】:** %s\n" +
            "   > - **【错误】:** %s\n" +
            "   > - **【参数】:** %s\n" +
            "   > - **【处理人】** ";

    @Override
    public void notifyEvent(MonitorEventMessage message) {
        this.sendMessage(message);
    }

    private void sendMessage(MonitorEventMessage message) {
        FlyBook flyBook = FlyBook.builder().content(FlyBook.MarkDown.builder().text(MARKDOWN_TEMPLATE).build()).build();
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json;charset=UTF-8"));
        HttpEntity<FlyBook> httpEntity = new HttpEntity<>(flyBook, headers);
        REST_TEMPLATE.postForEntity(WEB_HOOK, httpEntity, String.class);
    }
}
