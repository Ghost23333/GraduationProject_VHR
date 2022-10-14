package com.ch.vhr.model;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName Meta.java
 * @Description TODO
 * @createTime 2022年03月09日 21:35:00
 */
public class Meta {
    private Boolean keepAlive;

    private Boolean requireAuth;

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}
