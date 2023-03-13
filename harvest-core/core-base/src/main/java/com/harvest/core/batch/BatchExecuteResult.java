package com.harvest.core.batch;

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

    @ApiModelProperty("失败错误记录")
    private List<ReasonMap<T>> errorList;

    @ApiModelProperty("执行成功记录")
    private List<ReasonMap<T>> successList;

    public static BatchExecuteResult<String> empty() {
        return new BatchExecuteResult<>();
    }

    public BatchExecuteResult() {
        this.successCount = new AtomicInteger();
        this.failCount = new AtomicInteger();
        this.errorList = Collections.synchronizedList(Lists.newArrayList());
        this.successList = Collections.synchronizedList(Lists.newArrayList());
    }

    public BatchExecuteResult(int count) {
        this.count = count;
        this.successCount = new AtomicInteger();
        this.failCount = new AtomicInteger();
        this.errorList = Collections.synchronizedList(Lists.newArrayList());
        this.successList = Collections.synchronizedList(Lists.newArrayList());
    }

    public BatchExecuteResult(int count, AtomicInteger successCount, AtomicInteger failCount, List<ReasonMap<T>> errorList, List<ReasonMap<T>> successList) {
        this.count = count;
        this.successCount = successCount;
        this.failCount = failCount;
        this.errorList = errorList;
        this.successList = successList;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ReasonMap<T> extends BatchId implements Serializable {

        private static final long serialVersionUID = -5616278702464428044L;

        @ApiModelProperty("键值 eg:orderNo")
        private T key;

        @ApiModelProperty("异常原因")
        private String reason;

        @ApiModelProperty("异常")
        private Throwable e;

        @Override
        public String toString() {
            return new StringJoiner(", ", ReasonMap.class.getSimpleName() + "[", "]")
                    .add("key=" + key)
                    .add("reason='" + reason + "'")
                    .add("e=" + e)
                    .add("id=" + id)
                    .toString();
        }
    }
}