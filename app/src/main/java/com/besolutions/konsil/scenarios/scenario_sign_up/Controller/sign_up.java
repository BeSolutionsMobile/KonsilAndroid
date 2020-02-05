package com.besolutions.konsil.scenarios.scenario_sign_up.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.local_data.send_data;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.scenarios.scenario_sign_up.model.signup_model;
import com.besolutions.konsil.scenarios.scenario_splash_screen.Controller.splash_screen;
import com.besolutions.konsil.utils.utils;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;


public class sign_up extends AppCompatActivity implements View.OnClickListener, NetworkInterface {
    Button signup;
    EditText username, phone, email, password;
    signup_model signup_model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        signup = (Button) findViewById(R.id.signup);
        username = (EditText) findViewById(R.id.username);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signup) {

            //VALIDATION IN ALL INPUTS OF SIGN UP
            if (username.getText().length() < 6) {
                username.setError("Username id too short!");
                yoyo(R.id.username);
            } else if (phone.getText().length() < 11) {
                phone.setError("phone id too short!");
                yoyo(R.id.phone);
            } else if (email.getText().length() < 11) {
                email.setError("email id too short!");
                yoyo(R.id.email);
            } else if (password.getText().length() < 6) {
                password.setError("password id too short!");
                yoyo(R.id.password);
            } else {
                //COMPLETE MOBILE TOKEN AND LANGUAGE
                //GET DEVICE LANGUAGE
                String lang = new saved_data().get_lan(sign_up.this);
                new Apicalls(sign_up.this, this).insertUser(username.getText().toString(), phone.getText().toString(), email.getText().toString(), password.getText().toString(), "2", "", "en", "ssss");
            }
        }

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        Toast.makeText(this, model.getResponse(), Toast.LENGTH_SHORT).show();
      /*  signup_model = new signup_model(model.getJsonObject());

        //GET TOKEN AND SAVE IT IN LOCAL DATA
        send_data.save_token(sign_up.this, signup_model.getToken());

        String registed_suc = getResources().getString(R.string.registed_suc);
        Toasty.success(sign_up.this, registed_suc, Toasty.LENGTH_SHORT);
*/

    }

    @Override
    public void OnError(VolleyError error) {

    }


    //YOYO SHAKE
    void yoyo(int id) {
        YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(1)
                .playOn(findViewById(id));
    }

    //RETURN TOKEN KEY
    String token;

    public String firebase_token() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = instanceIdResult.getToken();
                // send it to server
            }
        });
        return token;
    }
}
