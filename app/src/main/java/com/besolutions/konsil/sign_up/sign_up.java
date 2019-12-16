package com.besolutions.konsil.sign_up;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.besolutions.konsil.R;
import com.besolutions.konsil.doctor_list.doctor_list;

public class sign_up extends AppCompatActivity implements View.OnClickListener {
   Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.signup)
        {
            startActivity(new Intent(sign_up.this, doctor_list.class));
        }
    }
}
