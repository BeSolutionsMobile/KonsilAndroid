package com.besolutions.konsil.scenarios.scenario_be_a_doctor.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.utils.utils;

public class be_a_doctor extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.be_a_doctor);
        set_toolbar_name();

        CardView uplaod_img=(CardView)findViewById(R.id.upload_img);
        uplaod_img.setOnClickListener(this);
    }

    public void set_toolbar_name()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title=(TextView)findViewById(R.id.title);
        title.setText("Be A Doctor");
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.upload_img)
        {
            utils utils=new utils();
            utils.upload_image(this,1);
        }
    }
}
