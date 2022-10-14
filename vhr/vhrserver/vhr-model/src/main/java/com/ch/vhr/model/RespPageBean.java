package com.ch.vhr.model;

import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName RespPageBean.java
 * @Description TODO
 * @createTime 2022年04月14日 11:01:00
 */

//分页查询

public class RespPageBean {
    private Long total;
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
