package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.Controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.model.consulation_list;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.pattern.consulation_result_adapter;
import com.besolutions.konsil.utils.utils_adapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragement_msg extends Fragment {

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
        return view;
    }

    void get_data()
    {
        //ADD DATA
        ArrayList<consulation_list>arrayList=new ArrayList<>();
        arrayList.add(new consulation_list("1","Dr Ibraheem","Please send data",null));
        arrayList.add(new consulation_list("1","mahmoud saad","ok",null));
        arrayList.add(new consulation_list("1","Dr Saeed","done mr ",null));

        RecyclerView msg_list=(RecyclerView)view.findViewById(R.id.msg_list);
        utils_adapter utils_adapter=new utils_adapter();
        utils_adapter.Adapter(msg_list,new consulation_result_adapter(getActivity(),arrayList),getActivity());
    }

}
