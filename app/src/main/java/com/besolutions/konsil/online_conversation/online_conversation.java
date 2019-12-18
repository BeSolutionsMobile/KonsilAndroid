package com.besolutions.konsil.online_conversation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.besolutions.konsil.Consulation_result.consulation_result;
import com.besolutions.konsil.Consulation_result.fragment_files.fragment_files;
import com.besolutions.konsil.Consulation_result.fragment_message.fragement_msg;
import com.besolutions.konsil.Consulation_result.fragment_report.fragment_report;
import com.besolutions.konsil.R;
import com.besolutions.konsil.online_conversation.info.fragment_info;
import com.besolutions.konsil.online_conversation.report.fragment_report_online_c;
import com.besolutions.konsil.utils.utils;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;

public class online_conversation extends AppCompatActivity {
    RadioRealButtonGroup radioRealButtonGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_conversation);
        utils utils=new utils();
        utils.Replace_Fragment(new fragment_info(),R.id.frag,this);

        radioRealButtonGroup=(RadioRealButtonGroup)findViewById(R.id.radioRealButtonGroup_1);
        radioRealButtonGroup.setOnPositionChangedListener(new RadioRealButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(RadioRealButton button, int currentPosition, int lastPosition) {
                if(currentPosition==0)
                {
                    utils utils=new utils();
                    utils.Replace_Fragment(new fragment_info(),R.id.frag, online_conversation.this);
                }
                else if(currentPosition==1)
                {
                    utils utils=new utils();
                    utils.Replace_Fragment(new fragment_report_online_c(),R.id.frag,online_conversation.this);
                }
            }
        });
    }
}
