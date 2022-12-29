package com.harvest.core.domain.query;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 3:04 PM
 * @Description: TODO
 **/
public class PageQuery implements Serializable {

    private static final long serialVersionUID = 7475282646403859810L;

    @ApiModelProperty("页码")
    private int pageNo;

    @ApiModelProperty("每页记录数")
    private int pageSize;

    @ApiModelProperty("偏移量")
    private int from;

    @ApiModelProperty("偏移记录数")
    private int limit;

    @ApiModelProperty("是否加载全部数据")
    private boolean pickCount;

    private void computeFromLimit() {
        from = (pageNo - 1) * pageSize;
        limit = pageSize;
    }

    public PageQuery() {
    }

    /**
     * @param pageSize 总记录数
     * @param pageNo   当前页数据
     */
    public PageQuery(int pageNo, int pageSize) {
        this.setPageSize(pageSize);
        this.setPageNo(pageNo);
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
        this.computeFromLimit();
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 0) {
            pageSize = 0;
        }
        this.pageSize = pageSize;
        this.computeFromLimit();
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setPickCount(boolean pickCount) {
        this.pickCount = pickCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getFrom() {
        return from;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isPickCount() {
        return pickCount;
    }
}
