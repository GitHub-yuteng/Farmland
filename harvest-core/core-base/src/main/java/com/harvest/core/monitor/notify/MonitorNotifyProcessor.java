package com.harvest.core.monitor.notify;

import com.harvest.core.monitor.domain.MonitorEventMessage;

/**
 * @Author: Alodi
 * @Date: 2023/3/15 2:57 PM
 * @Description: TODO
 **/
public interface MonitorNotifyProcessor {

    /**
     * 通知
     *
     * @param message
     */
    void notifyEvent(MonitorEventMessage message);

}
