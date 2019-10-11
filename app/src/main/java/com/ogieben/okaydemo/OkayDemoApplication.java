package com.ogieben.okaydemo;

import android.app.Application;

import com.itransition.protectoria.psa_multitenant.restapi.GatewayRestServer;
import com.ogieben.okaydemo.logger.OkayDemoLogger;
import com.protectoria.psa.PsaManager;
import com.protectoria.psa.dex.common.data.json.PsaGsonFactory;

public class OkayDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initPsa();
        initGatewayServer();
    }

    private void initPsa() {
        PsaManager psaManager = PsaManager.init(this, new OkayDemoLogger());
        psaManager.setPssAddress(BuildConfig.SERVER_URL);
    }

    private void initGatewayServer(){
        GatewayRestServer.init(new PsaGsonFactory().create(), BuildConfig.SERVER_URL + "/gateway/");
    }
}
