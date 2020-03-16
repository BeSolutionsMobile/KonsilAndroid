package com.besolutions.konsil.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import static com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen.dialog;


public class NetworkChangeReceiver extends BroadcastReceiver
{
    static  boolean net_connection= false; //USING TO CHECK IF FIRST TIME TO NET CONECTION BE TRUE OR NOT
    @Override
    public void onReceive(Context context, Intent intent)
    {
        try
        {
            if (isOnline(context)) {  //IF CONNECTION IS TRUE

                if(net_connection==true)  //AND IF CONNECTION FALSE AND BACK TO TRUE
                {
                    dialog(true);

//                ((AppCompatActivity)context).finish();
                    wait_time_to_back(context);  //CALL METHOD THAT WILL REFRESH PAGE BY 3 SECONDS AND CONNECTION FALSE
                }

            } else {
                net_connection = true;
                dialog(false);
                Log.e("keshav", "Conectivity Failure !!! ");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    //METHOD USED TO REFRESH PAGE AND SET CONNECTION FALSE WITH TIME
    void wait_time_to_back(final Context context)
    {
        Handler handler = new Handler();
        Runnable delayrunnable = new Runnable() {
            @Override
            public void run() {
                context.startActivity(((AppCompatActivity) context).getIntent()); //REFRESH ACTIVITY
                ((AppCompatActivity) context).overridePendingTransition(0, 0);//USING TO ANIMATE ZERO
                net_connection = false; //SET CONNECTION FALSE THAT MEAN ITS BACK TO CONNECTED

            }
        };
        handler.postDelayed(delayrunnable, 3000);
    }
}