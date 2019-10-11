package com.ogieben.okaydemo.fcm;

import android.content.Intent;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ogieben.okaydemo.data.repositories.PreferenceRepo;
import com.ogieben.okaydemo.ui.main.MainActivity;

public class OkayDemoFirebaseMessagingService extends FirebaseMessagingService {

    public static String ACTIVITY_WAKE_UP_KEY = "wake_up_key";

    public OkayDemoFirebaseMessagingService() {
        super();
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        if(!TextUtils.isEmpty(s)) {
           new PreferenceRepo(getApplicationContext()).putAppPNS(s);
        }
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage != null) {
            if(remoteMessage.getData() != null) {
                NotificationDataContent notificationDataContent = NotificationHandler.extractRemoteData(remoteMessage);
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(ACTIVITY_WAKE_UP_KEY, Long.valueOf(notificationDataContent.getSessionId()));
                startActivity(intent);
            }
        }
    }
}
