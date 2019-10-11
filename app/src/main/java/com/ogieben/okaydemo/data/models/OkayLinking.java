package com.ogieben.okaydemo.data.models;

public class OkayLinking {

    private String linkingCode;
    private String linkingQrImg;
    private Status status;

    public OkayLinking(String linkingCode, String linkingQrImg, Status status) {
        this.linkingCode = linkingCode;
        this.linkingQrImg = linkingQrImg;
        this.status = status;
    }

    public String getLinkingCode() {
        return linkingCode;
    }

    public void setLinkingCode(String linkingCode) {
        this.linkingCode = linkingCode;
    }

    public String getLinkingQrImg() {
        return linkingQrImg;
    }

    public void setLinkingQrImg(String linkingQrImg) {
        this.linkingQrImg = linkingQrImg;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
