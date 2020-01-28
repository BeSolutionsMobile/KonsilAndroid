package com.besolutions.konsil.scenarios.scenario_make_complaint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;

import java.util.ArrayList;

public class make_a_complaint extends AppCompatActivity implements View.OnClickListener {
    Button submit;
    Spinner make_complaint_spi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_a_complaint);
        submit=(Button)findViewById(R.id.make_complaint);
        make_complaint_spi=(Spinner)findViewById(R.id.make_complaint_spi);

        set_spinner_data();
        submit.setOnClickListener(this);

        TextView   title=(TextView)findViewById(R.id.title);
        String make_complaint = getResources().getString(R.string.make_a_complaint);
        title.setText(make_complaint);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.make_complaint)
        {
            startActivity(new Intent(this, main_screen.class));
        }
    }

    //ADD DATA TO SPINNER
    void set_spinner_data()
    {
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("first reason");
        arrayList.add("second reason");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,arrayList);
        make_complaint_spi.setAdapter(adapter);

    }
}
