package com.besolutions.konsil.utils;

import android.app.Application;

public class my_application_status extends Application {

    private static my_application_status mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized my_application_status getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(UpdateReceiver.ConnectivityReceiverListener listener) {
        UpdateReceiver.connectivityReceiverListener = listener;
    }
}
