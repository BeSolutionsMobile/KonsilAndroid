package com.besolutions.konsil.scenarios.scenario_payment.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.besolutions.konsil.R;

import java.util.ArrayList;
import java.util.List;

public class payment extends AppCompatActivity implements View.OnClickListener {
   Button pay;
   Spinner cardtype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        pay=(Button)findViewById(R.id.pay);
        cardtype=(Spinner)findViewById(R.id.cardtype);
        pay.setOnClickListener(this);

        TextView title =(TextView)findViewById(R.id.title);
        String Payment = getResources().getString(R.string.payment);
        title.setText(Payment);

        //SET SPINNER DATA
        set_spinner_data();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.pay)
        {
           loading loading=new loading();
           loading.dialog(payment.this,R.layout.successful_payment,.80);
        }
    }

    //ADD DATA TO SPINNER
    void set_spinner_data()
    {
        ArrayList<String>arrayList=new ArrayList<>();
        arrayList.add("VISA");
        arrayList.add("MASTER CARD");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,arrayList);
        cardtype.setAdapter(adapter);

    }
}
