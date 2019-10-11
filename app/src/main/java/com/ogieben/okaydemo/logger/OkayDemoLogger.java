package com.ogieben.okaydemo.logger;

import android.util.Log;

import com.protectoria.psa.dex.common.utils.logger.ExceptionLogger;

public class OkayDemoLogger implements ExceptionLogger {

    @Override
    public void exception(String s, Exception e) {
        Log.e("SET ID-: ", "Successfully set user identificator " + s);
    }

    @Override
    public void setUserIdentificator(String s) {
        Log.e("SET ID-: ", "Successfully set user identificator  " + s);
    }
}
