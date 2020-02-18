package com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.report.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_make_complaint.Controller.make_a_complaint;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_report_online_c extends Fragment implements View.OnClickListener {


    public fragment_report_online_c() {
        // Required empty public constructor
    }

  View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_report_online_c, container, false);
        Button make_complaint=(Button)view.findViewById(R.id.make_complaint);
        make_complaint.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.make_complaint)
        {
            startActivity(new Intent(getActivity(), make_a_complaint.class));
        }
    }
}
