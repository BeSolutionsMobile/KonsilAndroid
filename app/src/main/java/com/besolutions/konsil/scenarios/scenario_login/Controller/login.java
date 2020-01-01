package com.besolutions.konsil.scenarios.scenario_login.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.doctor_list;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.scenarios.secnario_fingerprint.Controller.fingerprint;
import com.besolutions.konsil.scenarios.scenario_sign_up.Controller.sign_up;

public class login extends AppCompatActivity implements View.OnClickListener {
   Button login;
   CheckBox check;
   TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login =(Button)findViewById(R.id.login);
        check =(CheckBox)findViewById(R.id.check);
        signup =(TextView)findViewById(R.id.signup);

        login.setOnClickListener(this);
        check.setOnClickListener(this);
        signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.login)
        {
            loading loading=new loading();
            loading.dialog(login.this,R.layout.successful_login,.80);
        }
        else if(v.getId()==R.id.check)
        {
            startActivity(new Intent(this, fingerprint.class));
        }
        else if(v.getId()==R.id.signup)
        {
            startActivity(new Intent(this, sign_up.class));
        }
    }
}
