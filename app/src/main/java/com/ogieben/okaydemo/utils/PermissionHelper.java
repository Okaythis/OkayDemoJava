package com.ogieben.okaydemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermissionHelper {

    private final int REQUEST_CODE = 204;
    private Activity activity;

    public PermissionHelper(Activity activity) {
        this.activity = activity;
    }

    public boolean hasPermissions(Context context, String... permissions) {
        for (String permission: permissions) {
            if(ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    public void requestPermissions(String... permission) {
        ActivityCompat.requestPermissions(activity, permission, REQUEST_CODE);
    }
}
