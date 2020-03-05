package com.besolutions.konsil.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class UpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean is_connect = false;

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            if (netInfo != null) {
                netInfo.isConnected();
                is_connect = true;

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            is_connect = false;

        }
        intent = new Intent("broadCastName");
        intent.putExtra("status", is_connect);
        context.sendBroadcast(intent);
    }
}
