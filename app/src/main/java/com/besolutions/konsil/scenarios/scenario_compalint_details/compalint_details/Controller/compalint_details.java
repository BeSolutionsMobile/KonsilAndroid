package com.besolutions.konsil.scenarios.scenario_compalint_details.compalint_details.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.besolutions.konsil.network_check_status.regist_network_broadcast;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.model.consulation_list;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_compalint_details.compalint_details.pattern.message_adapter;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.utils.utils_adapter;

import java.util.ArrayList;

public class compalint_details extends AppCompatActivity {

    RecyclerView message_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compalint_details);
        get_data();

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(compalint_details.this);
    }

    void get_data()
    {
        //ADD DATA
        ArrayList<consulation_list> arrayList=new ArrayList<>();
        arrayList.add(new consulation_list("1","Dr Ibraheem","Please send data",null));
        arrayList.add(new consulation_list("1","mahmoud saad","ok",null));
        arrayList.add(new consulation_list("1","Dr Saeed","done mr ",null));

        message_list=(RecyclerView)findViewById(R.id.message_list);
        utils_adapter utils_adapter=new utils_adapter();
        utils_adapter.Adapter(message_list,new message_adapter(this,arrayList),this);
    }
}
