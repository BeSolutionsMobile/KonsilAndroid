package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.besolutions.konsil.network_check_status.regist_network_broadcast;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_files.Controller.fragment_files;
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

        utils utils = new utils();
        utils.Replace_Fragment(new fragement_msg(), R.id.frag, this);
        radioRealButtonGroup = findViewById(R.id.radioRealButtonGroup_1);
        radioRealButtonGroup.setOnPositionChangedListener(new RadioRealButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(RadioRealButton button, int currentPosition, int lastPosition) {
                if (currentPosition == 0) {
                    utils utils = new utils();
                    utils.Replace_Fragment(new fragement_msg(), R.id.frag, consulation_result.this);
                } else if (currentPosition == 1) {
                    utils utils = new utils();
                    utils.Replace_Fragment(new fragment_report(), R.id.frag, consulation_result.this);
                } else if (currentPosition == 2) {
                    utils utils = new utils();
                    utils.Replace_Fragment(new fragment_files(), R.id.frag, consulation_result.this);
                }
            }
        });

        radioRealButtonGroup.setPosition(0);

        TextView title = findViewById(R.id.title);
        String req_online_con = getResources().getString(R.string.consualtion_details);
        title.setText(req_online_con);

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(consulation_result.this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
