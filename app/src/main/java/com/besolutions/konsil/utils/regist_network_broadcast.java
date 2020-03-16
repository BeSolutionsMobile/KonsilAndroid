package com.besolutions.konsil.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;


public class regist_network_broadcast  {
    NetworkChangeReceiver mNetworkReceiver = new NetworkChangeReceiver(); //DEFINE NETWORK RECIEVER

    public void registerNetworkBroadcastForNougat(Context context) {


        NetworkChangeReceiver mNetworkReceiver = new NetworkChangeReceiver(); //DEFINE NETWORK RECIEVER

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    public void unregisterNetworkChanges(Context context) {
        try {
            context.unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
