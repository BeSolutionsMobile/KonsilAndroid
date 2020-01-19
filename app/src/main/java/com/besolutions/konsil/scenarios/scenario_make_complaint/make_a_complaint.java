package com.besolutions.konsil.scenarios.scenario_make_complaint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;

public class make_a_complaint extends AppCompatActivity implements View.OnClickListener {
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_a_complaint);
        submit=(Button)findViewById(R.id.make_complaint);
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
}
