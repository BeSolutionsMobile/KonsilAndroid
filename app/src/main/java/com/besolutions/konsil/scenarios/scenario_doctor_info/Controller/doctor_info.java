package com.besolutions.konsil.scenarios.scenario_doctor_info.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.Controller.request_online_conversation;
import com.besolutions.konsil.scenarios.scenario_Consulation_request.Controller.consulation_request;


public class doctor_info extends AppCompatActivity implements View.OnClickListener {
   RatingBar ratings;
   Button request_consulation,online_res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_info);

        ratings=(RatingBar)findViewById(R.id.ratings);
        request_consulation=(Button)findViewById(R.id.request_consulation);
        online_res=(Button)findViewById(R.id.online_res);

        online_res.setOnClickListener(this);
        request_consulation.setOnClickListener(this);
        ratings.setRating(4);

        set_toolbar_name();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.request_consulation)
        {
            startActivity(new Intent(doctor_info.this, consulation_request.class));
        }
        else if(v.getId()==R.id.online_res)
        {
            startActivity(new Intent(doctor_info.this, request_online_conversation.class));
        }
    }

    public void set_toolbar_name()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title=(TextView)findViewById(R.id.title);
        title.setText("Doctor Info");
    }
}
