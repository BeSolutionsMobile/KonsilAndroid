package com.besolutions.konsil.scenarios.scenario_splash_Activity.Controller;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.utils.utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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


        Glide.with(this).asGif().load(R.raw.anim).listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                resource.setLoopCount(1);
                resource.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        //do whatever after specified number of loops complete
                    }
                });
                return false;
            }}).into(logoanim);





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

                logoanim.animate().yBy(500f);


                //GO TO SECOND ACTIVITY
                //new utils().splash_screen(splash_activity.this, splash_screen.class);
            }
        }).start();

    }
}