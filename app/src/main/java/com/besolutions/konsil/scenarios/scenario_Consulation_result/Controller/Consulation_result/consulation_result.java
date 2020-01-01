package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_files.fragment_files;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.Controller.fragement_msg;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.Controller.fragment_report;
import com.besolutions.konsil.R;
import com.besolutions.konsil.utils.utils;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;

public class consulation_result extends AppCompatActivity {
    RadioRealButtonGroup radioRealButtonGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulation_result);
        utils utils=new utils();
        utils.Replace_Fragment(new fragement_msg(),R.id.frag,this);
        radioRealButtonGroup=(RadioRealButtonGroup)findViewById(R.id.radioRealButtonGroup_1);
        radioRealButtonGroup.setOnPositionChangedListener(new RadioRealButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(RadioRealButton button, int currentPosition, int lastPosition) {
                if(currentPosition==0)
                {
                    utils utils=new utils();
                    utils.Replace_Fragment(new fragement_msg(),R.id.frag,consulation_result.this);
                }
                else if(currentPosition==1)
                {
                    utils utils=new utils();
                    utils.Replace_Fragment(new fragment_report(),R.id.frag,consulation_result.this);
                }
                else if(currentPosition==2)
                {
                    utils utils=new utils();
                    utils.Replace_Fragment(new fragment_files(),R.id.frag,consulation_result.this);
                }
            }
        });

        radioRealButtonGroup.setPosition(0);

    }
}
