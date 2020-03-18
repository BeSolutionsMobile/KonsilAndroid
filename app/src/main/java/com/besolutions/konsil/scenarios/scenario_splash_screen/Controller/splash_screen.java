package com.besolutions.konsil.scenarios.scenario_splash_screen.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.local_data.send_data;
import com.besolutions.konsil.scenarios.scenario_login.Controller.login;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.language_filter;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.scenarios.scenario_splash_screen.pattern.viewimage;
import com.besolutions.konsil.scenarios.secnario_fingerprint.Controller.fingerprint;
import com.besolutions.konsil.utils.utils;

import org.json.JSONException;

import me.relex.circleindicator.CircleIndicator;

public class splash_screen extends AppCompatActivity implements NetworkInterface {
    viewimage viewimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenario_welcome_tour);

        //CHANGE LANGUAGE
        new utils().set_language(new saved_data().get_lan(splash_screen.this), splash_screen.this);


        final ViewPager viewPager = findViewById(R.id.viewpager);
        viewimage = new viewimage(splash_screen.this);
        viewPager.setAdapter(viewimage);

        CircleIndicator circleIndicator = findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
        send_data.tour_status(splash_screen.this,true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(new saved_data().get_tour_status(splash_screen.this) == true)
        {
            setCheck_finger();
        }

    }

    //CHECK IF FINGER PRINT
    void setCheck_finger() {
        if(new saved_data().get_login_status(this) == true) { //CHECK IF LOGIN IS TRUE
            if (new saved_data().get_finger_print(this).equals("yes")) {  // CHECK IF FINGER PRINT TRUE
                startActivity(new Intent(splash_screen.this, fingerprint.class));
            }
            else {
                startActivity(new Intent(splash_screen.this, main_screen.class));
            }
        }
        else {
            startActivity(new Intent(splash_screen.this, login.class)); //GO TO LOGIN IF FALSE LOGIN
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        Toast.makeText(this, ""+model.getJsonObject(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnError(VolleyError error) {
        Toast.makeText(this, ""+error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();

    }


}
