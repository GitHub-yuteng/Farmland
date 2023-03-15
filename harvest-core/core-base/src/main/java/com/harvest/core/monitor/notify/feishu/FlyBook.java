package com.harvest.core.monitor.notify.feishu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2023/3/15 4:21 PM
 * @Description: TODO
 **/
@Data
@Builder
@AllArgsConstructor
public class FlyBook implements Serializable {

    private static final long serialVersionUID = -2751717760075575132L;

    private final String msg_type = "text";

    private MarkDown content;

    @Data
    @Builder
    @AllArgsConstructor
    public static class MarkDown implements Serializable {

        private static final long serialVersionUID = -5558448257494802930L;

        private String text;

    }
}
