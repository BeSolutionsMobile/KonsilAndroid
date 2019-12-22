package com.besolutions.konsil.scenarios.secnario_fingerprint.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.doctor_list;

import co.infinum.goldfinger.Error;
import co.infinum.goldfinger.Goldfinger;


public class fingerprint extends AppCompatActivity {
    Goldfinger goldfinger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fingerprint);

        goldfinger= new Goldfinger.Builder(fingerprint.this).build();
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (goldfinger.hasFingerprintHardware() && goldfinger.hasEnrolledFingerprint()) {

            authenticateUserFingerprint();

        }else
        {


        }
    }

    private void authenticateUserFingerprint() {
        final ImageView finger=(ImageView)findViewById(R.id.finger);
        goldfinger.authenticate(new Goldfinger.Callback() {


            @Override
            public void onSuccess(String value) {
                //login.setEnabled(true);
                Toast.makeText(fingerprint.this, "successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(fingerprint.this, doctor_list.class));
                finish();
            }

            @Override
            public void onError(Error error) {
                Toast.makeText(fingerprint.this, "Cant Access", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        goldfinger.cancel();
    }
}

