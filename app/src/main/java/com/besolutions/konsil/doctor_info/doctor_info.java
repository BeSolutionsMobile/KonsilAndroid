package com.besolutions.konsil.doctor_info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.besolutions.konsil.Consulation_request.consulation_request;
import com.besolutions.konsil.R;

public class doctor_info extends AppCompatActivity implements View.OnClickListener {
   RatingBar ratings;
   Button request_consulation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_info);

        ratings=(RatingBar)findViewById(R.id.ratings);
        request_consulation=(Button)findViewById(R.id.request_consulation);

        request_consulation.setOnClickListener(this);
        ratings.setRating(4);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.request_consulation)
        {
            startActivity(new Intent(doctor_info.this, consulation_request.class));
        }
    }
}
