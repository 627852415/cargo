package com.lxtx.framework.common.utils.sixx.model;

import java.util.List;

/**
 * 交易流水
 */
public class TransferResultListPojo {

    private Integer total;

    private Integer size;

    private Integer count;

    private Integer page;

    private List<TransferResultPojo> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<TransferResultPojo> getData() {
        return data;
    }

    public void setData(List<TransferResultPojo> data) {
        this.data = data;
    }
}
