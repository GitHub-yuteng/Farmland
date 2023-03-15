package com.harvest.core.monitor.notify.ding;

import com.harvest.core.monitor.domain.MonitorEventMessage;
import com.harvest.core.monitor.notify.MonitorNotifyProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/3/15 2:43 PM
 * @Description: TODO
 **/
@Component
public class DingTalkNotifyProcessor implements MonitorNotifyProcessor {

    @Override
    public void notifyEvent(MonitorEventMessage message) {

    }

}
