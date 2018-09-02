package com.byron.line.common.domain;

public class PageDto {
    Integer start;
    Integer limit;
    String sort;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        if (start == null) {
            start = 0;
        }
        this.start = start + 1;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        if (limit == null) {
            limit = 1000;
        }
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
