package com.besolutions.konsil.scenarios.scenario_payment.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.besolutions.konsil.R;

public class payment extends AppCompatActivity implements View.OnClickListener {
   Button pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        pay=(Button)findViewById(R.id.pay);
        pay.setOnClickListener(this);

        TextView title =(TextView)findViewById(R.id.title);
        String Payment = getResources().getString(R.string.payment);
        title.setText(Payment);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.pay)
        {
           loading loading=new loading();
           loading.dialog(payment.this,R.layout.successful_payment,.80);
        }
    }
}
