package com.harvest.core.batch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@ApiModel("可部分失败执行统计")
public class BatchExecuteResult<T> {

    @ApiModelProperty("任务总数")
    private int count;

    @ApiModelProperty("执行成功条数")
    private AtomicInteger successCount;

    @ApiModelProperty("是否允许继续执行失败数据, continueExec == true 时允许继续执行")
    private Boolean continueExec;

    @ApiModelProperty("执行失败条数")
    private AtomicInteger failCount;

    @ApiModelProperty("失败时错误信息")
    private List<ErrorReasonMap<T>> errorList;

    @ApiModelProperty("执行成功记录Key")
    private List<T> successKeyList;

    public BatchExecuteResult() {
        this.successCount = new AtomicInteger();
        this.failCount = new AtomicInteger();
        this.errorList = Collections.synchronizedList(Lists.newArrayList());
        this.successKeyList = Collections.synchronizedList(Lists.newArrayList());
    }

    public BatchExecuteResult(int count) {
        this.count = count;
        this.successCount = new AtomicInteger();
        this.failCount = new AtomicInteger();
        this.errorList = Collections.synchronizedList(Lists.newArrayList());
        this.successKeyList = Collections.synchronizedList(Lists.newArrayList());
    }

    public BatchExecuteResult(int count, AtomicInteger successCount, AtomicInteger failCount, List<ErrorReasonMap<T>> errorList, List<T> successKeyList) {
        this.count = count;
        this.successCount = successCount;
        this.failCount = failCount;
        this.errorList = errorList;
        this.successKeyList = successKeyList;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ErrorReasonMap<T> extends BatchId implements Serializable {

        private static final long serialVersionUID = -5616278702464428044L;

        @ApiModelProperty("键值 eg:orderNo")
        private T key;

        @ApiModelProperty("异常原因")
        private String reason;

        @JsonIgnore
        @ApiModelProperty("异常")
        private Throwable e;

        @Override
        public String toString() {
            return new StringJoiner(", ", ErrorReasonMap.class.getSimpleName() + "[", "]")
                    .add("key=" + key)
                    .add("reason='" + reason + "'")
                    .add("e=" + e)
                    .add("id=" + id)
                    .toString();
        }
    }
}