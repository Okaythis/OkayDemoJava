package com.ogieben.okaydemo.data.models;

public class AuthorizationResponse {

    private String sessionExternalId;
    private String status;

    public AuthorizationResponse(String sessionExternalId, String status) {
        this.sessionExternalId = sessionExternalId;
        this.status = status;
    }

    public String getSessionExternalId() {
        return sessionExternalId;
    }

    public void setSessionExternalId(String sessionExternalId) {
        this.sessionExternalId = sessionExternalId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
