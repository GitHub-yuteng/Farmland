package com.harvest.core.monitor.domain;

import com.harvest.core.monitor.enums.MemberContactEnum;
import com.harvest.core.monitor.enums.MonitorEventEnum;
import com.harvest.core.monitor.enums.MonitorLevelEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @Author: Alodi
 * @Date: 2023/1/29 5:19 PM
 * @Description: 钉钉事件消息
 **/
@Data
public class DingTalkEventMessage {

    @ApiModelProperty("公司Id")
    private long companyId;
    @ApiModelProperty("请求Id")
    private String requestId;
    @ApiModelProperty("监控名称")
    private String monitor;
    @ApiModelProperty("监控事件")
    private MonitorEventEnum event;
    @ApiModelProperty("异常级别")
    private MonitorLevelEnum level;
    @ApiModelProperty("执行耗时")
    private long executeCost;
    @ApiModelProperty("异常原因")
    private String cause;
    @ApiModelProperty("发生时间")
    private Date happenTime;
    @ApiModelProperty("发生节点信息")
    private String node;
    @ApiModelProperty("参数信息")
    private String params;

    @ApiModelProperty("重点关注人员")
    private Set<MemberContactEnum> atMembers;
}
