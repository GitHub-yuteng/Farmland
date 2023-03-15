package com.harvest.core.monitor.notify.feishu;

import com.harvest.core.monitor.domain.MonitorEventMessage;
import com.harvest.core.monitor.notify.AbstractMonitorNotifyProcessor;
import com.harvest.core.monitor.notify.MonitorNotifyProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Alodi
 * @Date: 2023/3/15 2:43 PM
 * @Description: TODO
 **/
@Component
public class FeiShuNotifyProcessor extends AbstractMonitorNotifyProcessor implements MonitorNotifyProcessor {

    private static final String WEB_HOOK = "https://open.feishu.cn/open-apis/bot/v2/hook/6c0f90a1-44cc-4802-8bac-1538e9685e3d";

    private final static RestTemplate REST_TEMPLATE = new RestTemplate();

    @Override
    public void notifyEvent(MonitorEventMessage message) {
        super.sendMessage(WEB_HOOK, message);
    }

}
