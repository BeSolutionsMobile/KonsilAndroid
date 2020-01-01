package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_make_complaint.make_a_complaint;
import com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.online_conversation;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_report extends Fragment implements View.OnClickListener {
   RecyclerView list;
   View view;
    public fragment_report() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_report, container, false);

        Button req_online_conv=(Button)view.findViewById(R.id.req_online_conv);
        Button make_a_complaint=(Button)view.findViewById(R.id.make_complaint);

        req_online_conv.setOnClickListener(this);
        make_a_complaint.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.req_online_conv)
        {
            startActivity(new Intent(getActivity(), online_conversation.class));
        }
        else if(v.getId()==R.id.make_complaint)
        {
            startActivity(new Intent(getActivity(), make_a_complaint.class));
        }
    }
}
