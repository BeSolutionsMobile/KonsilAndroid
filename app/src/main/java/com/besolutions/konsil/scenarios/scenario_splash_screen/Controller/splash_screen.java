package com.besolutions.konsil.scenarios.scenario_splash_screen.Controller;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_login.Controller.login;
import com.besolutions.konsil.scenarios.scenario_splash_screen.pattern.viewimage;
import com.besolutions.konsil.scenarios.scenario_welcome_tour.scenario_welcome_tour;
import com.besolutions.konsil.utils.utils;

import me.relex.circleindicator.CircleIndicator;

public class splash_screen extends AppCompatActivity {
    viewimage viewimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenario_welcome_tour);
        final ViewPager  viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewimage = new viewimage(splash_screen.this);
        viewPager.setAdapter(viewimage);

        CircleIndicator circleIndicator=(CircleIndicator)findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);
    }

}
