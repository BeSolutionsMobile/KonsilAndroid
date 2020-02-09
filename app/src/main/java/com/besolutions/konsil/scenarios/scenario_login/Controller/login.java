package com.besolutions.konsil.scenarios.scenario_login.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.local_data.send_data;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.doctor_list;
import com.besolutions.konsil.scenarios.scenario_login.model.login_root;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.scenarios.secnario_fingerprint.Controller.fingerprint;
import com.besolutions.konsil.scenarios.scenario_sign_up.Controller.sign_up;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import static com.besolutions.konsil.utils.utils_library.firebase_token;
import static com.besolutions.konsil.utils.utils_library.yoyo;

public class login extends AppCompatActivity implements View.OnClickListener, NetworkInterface {
    Button login;
    CheckBox check;
    TextView signup;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login = (Button) findViewById(R.id.login);
        check = (CheckBox) findViewById(R.id.check);
        signup = (TextView) findViewById(R.id.signup);


        login.setOnClickListener(this);
        check.setOnClickListener(this);
        signup.setOnClickListener(this);

        firebase_token();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            email = (EditText) findViewById(R.id.email);
            password = (EditText) findViewById(R.id.password);

            if (email.getText().toString().length() < 6) {
                email.setError("Please enter the correct mail!");
                yoyo(R.id.email, email);
            } else if (password.getText().toString().length() < 6) {
                password.setError("Please enter the correct password!");
                yoyo(R.id.password, password);
            } else {
                new Apicalls(login.this, login.this).loginUser(email.getText().toString(), password.getText().toString(), firebase_token());
            }
        } else if (v.getId() == R.id.check) {
            startActivity(new Intent(this, fingerprint.class));
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
        Toast.makeText(this, ""+model.getJsonObject(), Toast.LENGTH_SHORT).show();
        loading loading = new loading();
        loading.dialog(login.this, R.layout.successful_login, .80);


        login_root login_root = new login_root(model.getJsonObject());

        //GET TOKEN AND SAVE IT IN LOCAL DATA
        send_data.save_token(login.this, login_root.getToken());

        //toasty
        String logged_in_successfully = getResources().getString(R.string.logged_in_successfully);
        Toasty.success(login.this, logged_in_successfully, Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void OnError(VolleyError error) {
        if(error.networkResponse.statusCode==401)
        {
            Toasty.error(login.this, "Email or Password is incorrect", Toasty.LENGTH_SHORT).show();
        }
        else if(error.networkResponse.statusCode==405)
        {
            Toasty.error(login.this, "Email is Empty", Toasty.LENGTH_SHORT).show();
        }
        else if(error.networkResponse.statusCode==406)
        {
            Toasty.error(login.this, "Password is Empty", Toasty.LENGTH_SHORT).show();
        }
        else if(error.networkResponse.statusCode==407 )
        {
            Toasty.error(login.this, "Mobile Token is Empty", Toasty.LENGTH_SHORT).show();
        }
    }

}
