package com.besolutions.konsil.scenarios.scenario_splash_screen.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_login.Controller.login;
import com.besolutions.konsil.utils.utils;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        utils utils=new utils();
        utils.splash_screen(this, login.class);
    }
}
