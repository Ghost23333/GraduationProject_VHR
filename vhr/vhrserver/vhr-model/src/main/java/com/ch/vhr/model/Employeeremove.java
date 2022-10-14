package com.ch.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Employeeremove {
    private Integer id;

    private Integer eid;

    private Integer afterDepId;

    private String afterDepName;

    private Integer afterPosId;

    private String afterPosName;

    private Integer beforeDepId;
    private String beforeDepName;
    private Integer beforePosId;
    private String beforePosName;


    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date removeDate;

    private String reason;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getAfterDepId() {
        return afterDepId;
    }

    public void setAfterDepId(Integer afterDepId) {
        this.afterDepId = afterDepId;
    }

    public Integer getAfterPosId() {
        return afterPosId;
    }

    public void setAfterPosId(Integer afterPosId) {
        this.afterPosId = afterPosId;
    }

    public Date getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getBeforeDepId() {
        return beforeDepId;
    }

    public void setBeforeDepId(Integer beforeDepId) {
        this.beforeDepId = beforeDepId;
    }

    public Integer getBeforePosId() {
        return beforePosId;
    }

    public void setBeforePosId(Integer beforePosId) {
        this.beforePosId = beforePosId;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAfterDepName() {
        return afterDepName;
    }

    public void setAfterDepName(String afterDepName) {
        this.afterDepName = afterDepName;
    }

    public String getAfterPosName() {
        return afterPosName;
    }

    public void setAfterPosName(String afterPosName) {
        this.afterPosName = afterPosName;
    }

    public String getBeforeDepName() {
        return beforeDepName;
    }

    public void setBeforeDepName(String beforeDepName) {
        this.beforeDepName = beforeDepName;
    }

    public String getBeforePosName() {
        return beforePosName;
    }

    public void setBeforePosName(String beforePosName) {
        this.beforePosName = beforePosName;
    }
}