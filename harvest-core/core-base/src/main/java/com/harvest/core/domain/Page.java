package com.harvest.core.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 2:41 PM
 * @Description: 分页记录对象
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -6026191340387059611L;

    @ApiModelProperty("页码")
    private int pageNo;

    @ApiModelProperty("每页记录数")
    private int pageSize;

    @ApiModelProperty("偏移量")
    private int from;

    @ApiModelProperty("偏移记录数")
    private int limit;

    @ApiModelProperty("数据")
    private Collection<T> data;

    @ApiModelProperty("总数")
    private long count;

    @ApiModelProperty("是否还有下一页")
    private boolean hasMore;

    private void computeFromLimit() {
        from = (pageNo - 1) * pageSize;
        limit = pageSize;
    }

    public Page() {
    }

    /**
     * @param pageSize 总记录数
     * @param pageNo   当前页数据
     */
    public Page(int pageNo, int pageSize) {
        this.setPageSize(pageSize);
        this.setPageNo(pageNo);
    }

    /**
     * @param pageSize 总记录数
     * @param pageNo   当前页数据
     */
    public Page(int pageNo, int pageSize, Collection<T> data, long count) {
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
        this.setData(data);
        this.setCount(count);
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 0) {
            pageSize = 0;
        }
        this.pageSize = pageSize;
        this.computeFromLimit();
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
        this.computeFromLimit();
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getFrom() {
        return from;
    }

    public int getLimit() {
        return limit;
    }

    public Collection<T> getData() {
        return Objects.nonNull(data) ? data : Collections.EMPTY_LIST;
    }

    public long getCount() {
        return count;
    }

    public boolean isHasMore() {
        return hasMore;
    }
}
