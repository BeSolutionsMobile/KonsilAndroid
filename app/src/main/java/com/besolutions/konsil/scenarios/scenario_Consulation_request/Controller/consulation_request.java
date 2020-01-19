package com.besolutions.konsil.scenarios.scenario_Consulation_request.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.consulation_result;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.utils.utils;

public class consulation_request extends AppCompatActivity implements View.OnClickListener {
    ImageView upload_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulation_request);
        upload_img =(ImageView)findViewById(R.id.upload_img);
        upload_img.setOnClickListener(this);

        Button complete_req=(Button)findViewById(R.id.complete_req);
        complete_req.setOnClickListener(this);

        set_toolbar_name();


    }

    @Override
    public void onClick(View v) {
    if(v.getId()==R.id.upload_img)
    {
        utils utils=new utils();
        utils.upload_image(this,1);
    }
    else if(v.getId()==R.id.complete_req)
    {
        startActivity(new Intent(consulation_request.this, consulation_result.class));
    }
    }

    public void set_toolbar_name()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title=(TextView)findViewById(R.id.title);
        String con_req = getResources().getString(R.string.con_req);
        title.setText(con_req);
    }
}
