package com.mim_development.android.mimrestdemo;


import android.app.Application;

import com.mim_development.android.mimrest.MIMRest;

public class MIMRestDemoApplication extends Application{

    private static final boolean IS_SECURE = false;
    private static final String SERVER = "192.168.1.7:8080";
    private static final String APP_PATH = "RESTPoc/rest";
    private static final int CONNECTION_TIME_OUT_MILLIS = 5000;

    @Override
    public void onCreate() {
        super.onCreate();

        MIMRest.getInstance().initialize(
                IS_SECURE,
                SERVER,
                APP_PATH,
                CONNECTION_TIME_OUT_MILLIS,
                getApplicationContext());

    }
}
