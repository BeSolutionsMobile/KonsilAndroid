package com.besolutions.konsil.scenarios.secnario_fingerprint.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_login.Controller.loading;

import co.infinum.goldfinger.Error;
import co.infinum.goldfinger.Goldfinger;
import es.dmoral.toasty.Toasty;


public class
fingerprint extends AppCompatActivity {
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
            String cant_auth = getResources().getString(R.string.cant_auth);
            Toasty.error(fingerprint.this,cant_auth,Toasty.LENGTH_LONG).show();
        }
    }

    private void authenticateUserFingerprint() {
        final ImageView finger=(ImageView)findViewById(R.id.finger);
        goldfinger.authenticate(new Goldfinger.Callback() {


            @Override
            public void onSuccess(String value) {
                //login.setEnabled(true);
                loading loading = new loading();
                loading.dialog(fingerprint.this, R.layout.successful_login, .80);
            }

            @Override
            public void onError(Error error) {
                String cant_access = getResources().getString(R.string.cant_access);
                Toasty.error(fingerprint.this,cant_access,Toasty.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
        goldfinger.cancel();
    }
}

