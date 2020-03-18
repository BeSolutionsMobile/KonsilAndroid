package com.besolutions.konsil.scenarios.scenario_online_conversation.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;

import com.besolutions.konsil.network_check_status.regist_network_broadcast;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.info.Controller.fragment_info;
import com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.report.Controller.fragment_report_online_c;
import com.besolutions.konsil.utils.utils;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;

public class online_conversation extends AppCompatActivity implements NetworkInterface {
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
        radioRealButtonGroup.setPosition(0);
        set_toolbar_name();

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(online_conversation.this);
    }

    public void set_toolbar_name()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title=(TextView)findViewById(R.id.title);
        String online_con_details=getResources().getString(R.string.online_con_details);
        title.setText(online_con_details);
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

    }

    @Override
    public void OnError(VolleyError error) {

    }
}
