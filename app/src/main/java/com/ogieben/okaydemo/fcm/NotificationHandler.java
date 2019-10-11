package com.ogieben.okaydemo.fcm;

import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import static com.ogieben.okaydemo.BuildConfig.DEBUG;

public class NotificationHandler {

    private static final Gson gson = new Gson();

    public static NotificationDataContent extractRemoteData(RemoteMessage remoteData) {
        NotificationData notificationData = new NotificationData(NotificationType.creator(Integer.parseInt(remoteData.getData().get("type"))), remoteData.getData().get("data"));
        try {
            return gson.fromJson(notificationData.getData(), NotificationDataContent.class);
        }catch (Exception e){
            if(DEBUG) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
