package com.besolutions.konsil.scenarios.scenario_sign_up.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.local_data.send_data;
import com.besolutions.konsil.scenarios.scenario_login.Controller.loading;
import com.besolutions.konsil.scenarios.scenario_sign_up.model.UserInfo;
import com.besolutions.konsil.scenarios.scenario_sign_up.model.root_signup;
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
    root_signup root_signup;
    com.besolutions.konsil.scenarios.scenario_sign_up.model.UserInfo UserInfo;
    CheckBox accept_condition;
    Boolean check_box = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        signup = findViewById(R.id.signup);
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        accept_condition = findViewById(R.id.accept_condition);

        signup.setOnClickListener(this);

        //SET ON CHECK BOX
        accept_condition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                check_box = isChecked;
            }
        });

        firebase_token();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signup) {

            //VALIDATION IN ALL INPUTS OF SIGN UP
            if (username.getText().length() < 6) {
                username.setError("Username id too short!");
                yoyo(R.id.username);
            } else if (phone.getText().length() < 10) {
                phone.setError("phone id too short!");
                yoyo(R.id.phone);
            } else if (email.getText().length() < 6) {
                email.setError("email id too short!");
                yoyo(R.id.email);
            } else if (password.getText().length() < 8) {
                password.setError("password id too short!");
                yoyo(R.id.password);
            }
            else if(check_box == false)
            {
                Toasty.warning(sign_up.this,"You must accept conditions to Sign up",Toasty.LENGTH_LONG).show();
            }
            else {
                //COMPLETE MOBILE TOKEN AND LANGUAGE
                //GET DEVICE LANGUAGE
                String lang = new saved_data().get_lan(sign_up.this);
                new Apicalls(sign_up.this, this).insertUser(username.getText().toString(), phone.getText().toString(), email.getText().toString(), password.getText().toString(), "2", "", new saved_data().get_lan(this), firebase_token());
            }
        }

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
       // Toast.makeText(this, ""+model.getResponse(), Toast.LENGTH_SHORT).show();
        Gson gson=new Gson();
        root_signup = gson.fromJson(""+model.getResponse(),root_signup.class);

        UserInfo = root_signup.getUserInfo();

        //GET TOKEN AND SAVE IT IN LOCAL DATA
        send_data.save_token(sign_up.this, root_signup.getToken());

       //POP UP
        loading loading=new loading();
        loading.dialog(sign_up.this,R.layout.successful_login,.80);

        //SAVE PERSONAL INFO

        new send_data().send_name(this,UserInfo.getName());
        new send_data().send_email(this,UserInfo.getEmail());
        new send_data().send_phone(this,UserInfo.getPhone());



    }

    @Override
    public void OnError(VolleyError error) {
        if(error.networkResponse.statusCode == 402)
        {
            Toasty.error(sign_up.this,"Email Has Been Taken",Toasty.LENGTH_SHORT).show();
        }
        else if(error.networkResponse.statusCode == 500)
        {
            Toasty.error(sign_up.this," Error on Saving Data",Toasty.LENGTH_SHORT).show();
        }
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
