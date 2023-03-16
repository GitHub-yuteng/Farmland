package com.harvest.core.monitor.notify.log;

import com.harvest.core.monitor.domain.MonitorEventMessage;
import com.harvest.core.monitor.notify.MonitorNotifyProcessor;
import com.harvest.core.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/3/16 2:12 PM
 * @Description: TODO
 **/
@Component
public class LogNotifyProcessor implements MonitorNotifyProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogNotifyProcessor.class);

    @Override
    public void notifyEvent(MonitorEventMessage message) {
        LOGGER.warn("Monitor#" + JsonUtils.object2Json(message));
    }

}
