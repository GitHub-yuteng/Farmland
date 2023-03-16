package com.harvest.core.monitor.domain;

import com.harvest.core.context.Context;
import com.harvest.core.domain.CompanyId;
import com.harvest.core.monitor.enums.MemberContactEnum;
import com.harvest.core.monitor.enums.MonitorEventEnum;
import com.harvest.core.monitor.enums.MonitorLevelEnum;
import com.harvest.core.monitor.enums.MonitorTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @Author: Alodi
 * @Date: 2023/1/29 5:19 PM
 * @Description: 事件消息
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MonitorEventMessage extends CompanyId {

    private static final long serialVersionUID = -1381850067122598748L;

    @ApiModelProperty("请求Id")
    private String requestId;
    @ApiModelProperty("监控名称")
    private String monitor;
    @ApiModelProperty("监控事件")
    private MonitorEventEnum event;
    @ApiModelProperty("监控类型")
    private MonitorTypeEnum type;
    @ApiModelProperty("异常级别")
    private MonitorLevelEnum level;
    @ApiModelProperty("执行耗时")
    private long executeCost;
    @ApiModelProperty("异常原因")
    private String cause;
    @ApiModelProperty("发生时间")
    private LocalDateTime happenTime;
    @ApiModelProperty("发生节点信息")
    private String node;
    @ApiModelProperty("参数信息")
    private String params;

    @ApiModelProperty("重点关注人员")
    private Set<MemberContactEnum> atMembers;

    public MonitorEventMessage(Context context) {
        this.companyId = context.getCompanyId();
        this.requestId = (String) context.get(Context.PreferenceName.requestId);
        this.happenTime = LocalDateTime.now();
    }
}
