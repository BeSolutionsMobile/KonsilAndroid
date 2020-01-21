package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.model.consulation_list;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.pattern.consulation_result_adapter;
import com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.online_conversation;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.Controller.request_online_conversation;
import com.besolutions.konsil.utils.utils_adapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragement_msg extends Fragment implements View.OnClickListener {

   RecyclerView msg_list;
   View view;

    public fragement_msg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragement_msg, container, false);
        get_data();

        Button req_online_conv =(Button)view.findViewById(R.id.req_online_conv);
        req_online_conv.setOnClickListener(this);

        return view;
    }

    void get_data()
    {
        //ADD DATA
        ArrayList<consulation_list>arrayList=new ArrayList<>();
        arrayList.add(new consulation_list("1","Dr Ibraheem","Please send data","https://img.medscape.com/thumbnail_library/dt_181213_sad_depressed_pensive_doctor_800x450.jpg"));
        arrayList.add(new consulation_list("1","mahmoud saad","ok","https://assets.entrepreneur.com/content/3x2/2000/20190502194704-ent19-june-editorsnote.jpeg"));
        arrayList.add(new consulation_list("1","Dr Saeed","done mr ","https://img.medscape.com/thumbnail_library/dt_181213_sad_depressed_pensive_doctor_800x450.jpg"));

        RecyclerView msg_list=(RecyclerView)view.findViewById(R.id.msg_list);
        utils_adapter utils_adapter=new utils_adapter();
        utils_adapter.Adapter(msg_list,new consulation_result_adapter(getActivity(),arrayList),getActivity());

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.req_online_conv)
        {
            startActivity(new Intent(getActivity(), request_online_conversation.class));
        }
    }
}
