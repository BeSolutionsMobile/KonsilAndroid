package com.besolutions.konsil.scenarios.secnario_fingerprint.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_login.Controller.loading;
import com.besolutions.konsil.scenarios.scenario_login.Controller.login;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;

import co.infinum.goldfinger.Error;
import co.infinum.goldfinger.Goldfinger;
import es.dmoral.toasty.Toasty;


public class
fingerprint extends AppCompatActivity {
    Goldfinger goldfinger;
    int error_print_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fingerprint);

        goldfinger = new Goldfinger.Builder(fingerprint.this).build();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (goldfinger.hasFingerprintHardware() && goldfinger.hasEnrolledFingerprint()) {

            authenticateUserFingerprint();


        } else {
            String cant_auth = getResources().getString(R.string.cant_auth);
            Toasty.error(fingerprint.this, cant_auth, Toasty.LENGTH_LONG).show();
        }
    }

    private void authenticateUserFingerprint() {
        //DEFINE ALL VARS
        final ImageView finger = findViewById(R.id.finger);
        final LottieAnimationView unlock_animation = findViewById(R.id.finger_unlock);

        goldfinger.authenticate(new Goldfinger.Callback() {


            @Override
            public void onSuccess(String value) {
                finger.setVisibility(View.GONE); //HIDE FINGER IMAGE
                unlock_animation.playAnimation(); //DISPLAY ANIMATION LIBRARY
                handler_time_open_loading(); //METHOD THAT RESPONSE FOR GIVE TIME TO OPEN DIALOG


            }

            @Override
            public void onError(Error error) {
                error_print_num ++;
                error_auth(error_print_num); // CALL ERROR AUTH WILL GO TO LOGIN
                String cant_access = getResources().getString(R.string.cant_access);
                Toasty.error(fingerprint.this, cant_access, Toasty.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
        goldfinger.cancel();
    }

    //CALLING LOADING AND GO TO MAIN SCREEN
    void handler_time_open_loading()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(fingerprint.this, main_screen.class));  //START NEW ACTIVITY THAT WILL GO TO
            }
        }, 4500);
    }

    //CHECK IF ERROR NUM EQUAL THERE GO TO LOGIN
    void error_auth(int num)
    {
        if(num == 3)
        {
            startActivity(new Intent(fingerprint.this, login.class));  //START NEW ACTIVITY THAT WILL GO TO LOGIN
            finish();
        }
    }
}

