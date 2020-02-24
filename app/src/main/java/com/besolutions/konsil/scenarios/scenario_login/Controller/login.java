package com.besolutions.konsil.scenarios.scenario_login.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;

import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.local_data.send_data;

import com.besolutions.konsil.scenarios.scenario_login.model.UserInfo;
import com.besolutions.konsil.scenarios.scenario_login.model.login_root;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.scenarios.secnario_fingerprint.Controller.fingerprint;
import com.besolutions.konsil.scenarios.scenario_sign_up.Controller.sign_up;
import com.besolutions.konsil.utils.utils;
import com.google.gson.Gson;


import es.dmoral.toasty.Toasty;

import static com.besolutions.konsil.utils.utils_library.firebase_token;
import static com.besolutions.konsil.utils.utils_library.yoyo;

public class login extends AppCompatActivity implements View.OnClickListener, NetworkInterface {
    Button login;
    CheckBox check;
    TextView signup;
    EditText email, password;
    UserInfo userInfo;
    CheckBox check_finger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login = findViewById(R.id.login);
        check = findViewById(R.id.check);
        signup = findViewById(R.id.signup);
        check_finger = findViewById(R.id.check);


        login.setOnClickListener(this);
        check.setOnClickListener(this);
        signup.setOnClickListener(this);

        firebase_token();

        //SET FINGER PRINT
        check_finger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                email = findViewById(R.id.email);
                password = findViewById(R.id.password);

                new send_data().finger_print(login.this, "yes");

            }
        });

        //SET FINGER PRINT
        setCheck_finger();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            email = findViewById(R.id.email);
            password = findViewById(R.id.password);

            if (email.getText().toString().length() < 6) {
                email.setError("Please enter the correct mail!");
                yoyo(R.id.email, email);
            } else if (password.getText().toString().length() < 6) {
                password.setError("Please enter the correct password!");
                yoyo(R.id.password, password);
            } else {

                new utils().set_dialog(login.this);  //CALL PROGRESS DIALOG

                //CALL SERVER
                new Apicalls(login.this, login.this).loginUser(email.getText().toString(), password.getText().toString(), firebase_token());
            }
        } else if (v.getId() == R.id.signup) {
            startActivity(new Intent(this, sign_up.class));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        new utils().dismiss_dialog(login.this);  //DISMISS PROGRESS DIALOG


        loading loading = new loading();
        loading.dialog(login.this, R.layout.successful_login, .80);

        Gson gson = new Gson();

        login_root login_root = gson.fromJson(model.getResponse(), login_root.class);

        //GET TOKEN AND SAVE IT IN LOCAL DATA
        send_data.save_token(login.this, login_root.getToken());

        //SAVE PERSONAL INFO
        userInfo = login_root.getUserInfo();

        new send_data().send_name(this, userInfo.getName());
        new send_data().send_email(this, userInfo.getEmail());
        new send_data().send_phone(this, userInfo.getPhone());

        new send_data().login_status(login.this, true);  //SET TRUE TO MAKE LOGIN AFTER FIRST LOGIN



    }

    @Override
    public void OnError(VolleyError error) {
        if (error.networkResponse.statusCode == 401) {
            Toasty.error(login.this, "Email or Password is incorrect", Toasty.LENGTH_SHORT).show();
        } else if (error.networkResponse.statusCode == 405) {
            Toasty.error(login.this, "Email is Empty", Toasty.LENGTH_SHORT).show();
        } else if (error.networkResponse.statusCode == 406) {
            Toasty.error(login.this, "Password is Empty", Toasty.LENGTH_SHORT).show();
        } else if (error.networkResponse.statusCode == 407) {
            Toasty.error(login.this, "Mobile Token is Empty", Toasty.LENGTH_SHORT).show();
        }
    }

    //CHECK IF FINGER PRINT
    void setCheck_finger() {
        if(new saved_data().get_login_status(this) == true) {
            if (new saved_data().get_finger_print(this).equals("yes")) {
                startActivity(new Intent(login.this, fingerprint.class));
            }
            else {
                startActivity(new Intent(login.this, main_screen.class));
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
