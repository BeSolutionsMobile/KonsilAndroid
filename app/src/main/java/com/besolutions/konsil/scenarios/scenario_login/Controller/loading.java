package com.besolutions.konsil.scenarios.scenario_login.Controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;

import org.json.JSONException;

import java.util.Objects;


public class loading implements NetworkInterface {
    public void dialog(final Context context, int resource, double widthh) {
        final Dialog dialog = new Dialog(context);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);

        Activity activity = (Activity)context;
        if(!activity.isFinishing()) {
            dialog.show();
        }

       change_lan(context); //DATA CAME FROM SERVER

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                context.startActivity(new Intent(context, main_screen.class));
                dialog.dismiss();
            }
        }, 2000);
    }


    //CHANGE LANGUAGE USING API
    void change_lan(Context context)
    {

                try {
                    new Apicalls(context, loading.this).change_lan(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

    }

    @Override
    public void OnError(VolleyError error) {

    }
}

