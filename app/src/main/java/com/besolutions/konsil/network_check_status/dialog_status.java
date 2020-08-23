package com.besolutions.konsil.network_check_status;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.besolutions.konsil.R;

public class dialog_status {

    static int call_api_num = 0;

    public static void dialog(boolean value, Context context){
       final TextView tv_check_connection = ((AppCompatActivity)context).findViewById(R.id.tv_check_connection);
        if(value){

            call_api_num = 1;
            tv_check_connection.setText("Connecting !!!");
            tv_check_connection.setBackgroundColor(Color.GREEN);
            tv_check_connection.setTextColor(Color.BLACK);
            tv_check_connection.setVisibility(View.VISIBLE);



            Handler handler = new Handler();
            Runnable delayrunnable = new Runnable() {
                @Override
                public void run() {
                    tv_check_connection.setVisibility(View.GONE);
                }
            };
            handler.postDelayed(delayrunnable, 3000);
        }else {
            tv_check_connection.setVisibility(View.VISIBLE);
            tv_check_connection.setText("Could not Connect to internet");
            tv_check_connection.setBackgroundColor(Color.RED);
            tv_check_connection.setTextColor(Color.WHITE);
        }
    }
}
