package com.ogieben.okaydemo.fcm;

import java.io.Serializable;

public class NotificationDataContent implements Serializable {

    private String tenantId;
    private String sessionId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
