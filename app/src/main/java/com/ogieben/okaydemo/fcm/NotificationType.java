package com.ogieben.okaydemo.fcm;

public enum NotificationType {
    WAKE_UP(10), AUTH_RESULT(20), UNDEFINED(0);
    private int code;

    NotificationType(int code) {
        this.code = code;
    }

    public static NotificationType creator(int code) {
        switch(code) {
            case 10 :
                return NotificationType.WAKE_UP;
            case 20:
                return NotificationType.AUTH_RESULT;
            default: return NotificationType.UNDEFINED;
        }
    }
}
