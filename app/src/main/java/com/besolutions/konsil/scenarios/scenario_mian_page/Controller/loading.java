package com.besolutions.konsil.scenarios.scenario_mian_page.Controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;

import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.scenarios.scenario_login.Controller.login;
import com.besolutions.konsil.utils.utils;


public class loading {
    public void dialog(final Context context, int resource, double widthh) {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.show();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                context.startActivity(new Intent(context, login.class));
                dialog.dismiss();
            }
        }, 2000);
    }
}
