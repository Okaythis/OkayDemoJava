package com.ogieben.okaydemo.data.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.itransition.protectoria.psa_multitenant.data.SpaStorage;

public class PreferenceRepo implements SpaStorage {

    private SharedPreferences prefStorage;
    private static String PREFERENCE_KEY = "preference_key";
    private static String APP_PNS = "app_pns";
    private static String EXTERNAL_ID = "external_id";
    private static String PUB_PSS_B64 = "pub_pss_b64";
    private static String ENROLLMENT_ID = "enrollment_id";
    private static String INSTALLATION_ID = "installation_id";

    public PreferenceRepo(Context context) {
        this.prefStorage = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public void putAppPNS(String s) {
        prefStorage.edit().putString(APP_PNS, s).commit();
    }

    @Override
    public String getAppPNS() {
        return prefStorage.getString(APP_PNS, "");
    }

    @Override
    public void putExternalId(String s) {
        prefStorage.edit().putString(EXTERNAL_ID, s).commit();
    }

    @Override
    public String getExternalId() {
        return prefStorage.getString(EXTERNAL_ID, "");
    }

    @Override
    public void putEnrollmentId(String s) {
        prefStorage.edit().putString(ENROLLMENT_ID, s).commit();
    }

    @Override
    public String getEnrollmentId() {
        return prefStorage.getString(ENROLLMENT_ID, "");
    }

    @Override
    public void putInstallationId(String s) {
        prefStorage.edit().putString(INSTALLATION_ID, s).commit();
    }

    @Override
    public String getInstallationId() {
        return prefStorage.getString(INSTALLATION_ID, "");
    }

    @Override
    public void putPubPssBase64(String s) {
        prefStorage.edit().putString(PUB_PSS_B64, s).commit();
    }

    @Override
    public String getPubPssBase64() {
        return prefStorage.getString(PUB_PSS_B64, "");
    }
}


