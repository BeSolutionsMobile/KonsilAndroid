package com.besolutions.konsil.scenarios.scenario_splash_Activity.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
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

        move_to_top();

        //CHANGE LANGUAGE
        new utils().set_language(new saved_data().get_lan(splash_activity.this), splash_activity.this);

    }


    //MOVE TO TOP AFTER 5 SECONDS
    void move_to_top()
    {

        new Thread(new Runnable() {
            public void run() {
                try {
                    // sleep during 800ms
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // start moving to top
                Animation an = new TranslateAnimation(0,0,0,-700);//0,0 is the current       coordinates
                an.setFillAfter(true);// to keep the state after animation is finished
                logoanim.startAnimation(an);// to start animation obviously

                //GO TO SECOND ACTIVITY
                new utils().splash_screen(splash_activity.this, splash_screen.class);
            }
        }).start();

    }
}