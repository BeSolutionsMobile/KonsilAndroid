package com.besolutions.konsil.scenarios.scenario_splash_screen.Controller;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.scenarios.scenario_splash_screen.pattern.viewimage;
import com.besolutions.konsil.utils.utils;

import me.relex.circleindicator.CircleIndicator;

public class splash_screen extends AppCompatActivity {
    viewimage viewimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenario_welcome_tour);
        new utils().set_language(new saved_data().get_lan(splash_screen.this),splash_screen.this);
        final ViewPager  viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewimage = new viewimage(splash_screen.this);
        viewPager.setAdapter(viewimage);

        CircleIndicator circleIndicator=(CircleIndicator)findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
