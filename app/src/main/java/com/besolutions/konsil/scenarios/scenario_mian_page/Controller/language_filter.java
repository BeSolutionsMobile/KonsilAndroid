package com.besolutions.konsil.scenarios.scenario_mian_page.Controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.local_data.send_data;
import com.besolutions.konsil.utils.utils;

import org.json.JSONException;

public class language_filter implements View.OnClickListener , NetworkInterface {

    Context context;

    void dialog_language(Context context, int resource, double widthh) {

        this.context = context;
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);

        //ENGLISH
        LinearLayout english = dialog.findViewById(R.id.english);
        english.setOnClickListener(this);

        //GERMANY
        LinearLayout germany = dialog.findViewById(R.id.germany);
        germany.setOnClickListener(this);

        dialog.show();
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.english)
        {
            send_data.send_lan(context, "en");
            new utils().set_language(new saved_data().get_lan(context),context); //CHANGE LANGUAGE

            //CALL SERVER
            try {
                new Apicalls(context,language_filter.this).change_lan(context);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            new loading().dialog(context,R.layout.language_changed,.70);


        } else if(v.getId() == R.id.germany)
        {
            send_data.send_lan(context, "gr");
            new utils().set_language(new saved_data().get_lan(context),context); //CHANGE LANGUAGE

            //CALL SERVER
            try {
                new Apicalls(context,language_filter.this).change_lan(context);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            new loading().dialog(context,R.layout.language_changed,.70);


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
