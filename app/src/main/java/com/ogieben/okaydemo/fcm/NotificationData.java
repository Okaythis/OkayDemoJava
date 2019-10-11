package com.ogieben.okaydemo.fcm;

public class NotificationData {

    public NotificationType type;
    public String data;

    public NotificationData(NotificationType type, String data) {
        this.type = type;
        this.data = data;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
