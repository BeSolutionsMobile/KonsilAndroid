package com.besolutions.konsil.scenarios.scenario_splash_Activity.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.scenarios.scenario_splash_screen.Controller.splash_screen;
import com.besolutions.konsil.utils.utils;
import com.bumptech.glide.Glide;

public class splash_activity extends AppCompatActivity {
 ImageView logoanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);

        logoanim = findViewById(R.id.logoanim);

        Glide.with(this).asGif().load(R.raw.anim).into(logoanim);


        //CHANGE LANGUAGE
        new utils().set_language(new saved_data().get_lan(splash_activity.this), splash_activity.this);

        new utils().splash_screen(this, splash_screen.class);
    }
}