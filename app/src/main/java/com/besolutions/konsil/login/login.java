package com.besolutions.konsil.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.doctor_list.doctor_list;
import com.besolutions.konsil.fingerprint.fingerprint;
import com.besolutions.konsil.sign_up.sign_up;

public class login extends AppCompatActivity implements View.OnClickListener {
   Button login;
   CheckBox check;
   TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login=(Button)findViewById(R.id.login);
        check=(CheckBox)findViewById(R.id.check);
        signup=(TextView)findViewById(R.id.signup);

        login.setOnClickListener(this);
        check.setOnClickListener(this);
        signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.login)
        {
            startActivity(new Intent(this, doctor_list.class));
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
