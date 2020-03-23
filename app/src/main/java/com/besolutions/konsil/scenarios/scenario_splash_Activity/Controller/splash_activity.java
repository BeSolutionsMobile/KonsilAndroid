package com.besolutions.konsil.scenarios.scenario_splash_Activity.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.scenarios.scenario_splash_screen.Controller.splash_screen;
import com.besolutions.konsil.utils.utils;

public class splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        //CHANGE LANGUAGE
        new utils().set_language(new saved_data().get_lan(splash_activity.this), splash_activity.this);

        new utils().splash_screen(this, splash_screen.class);
    }
}
