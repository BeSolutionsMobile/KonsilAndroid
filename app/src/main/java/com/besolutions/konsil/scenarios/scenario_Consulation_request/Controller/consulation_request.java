package com.besolutions.konsil.scenarios.scenario_Consulation_request.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.utils.utils;

public class consulation_request extends AppCompatActivity implements View.OnClickListener {
    ImageView upload_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulation_request);
        upload_img =(ImageView)findViewById(R.id.upload_img);
        upload_img.setOnClickListener(this);

        set_toolbar_name();


    }

    @Override
    public void onClick(View v) {
    if(v.getId()==R.id.upload_img)
    {
        utils utils=new utils();
        utils.upload_image(this,1);
    }
    }

    public void set_toolbar_name()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title=(TextView)findViewById(R.id.title);
        title.setText("Consultation Request");
    }
}
