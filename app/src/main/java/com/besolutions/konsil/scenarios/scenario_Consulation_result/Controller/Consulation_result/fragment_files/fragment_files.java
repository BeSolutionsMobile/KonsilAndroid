package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_files;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.model.file_list;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.file_result_adapter;
import com.besolutions.konsil.R;
import com.besolutions.konsil.utils.utils_adapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_files extends Fragment {
       View view;
       RecyclerView list;

    public fragment_files() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_files, container, false);
        getData();
        return view;
    }

    void getData()
    {
        ArrayList<file_list> arrayList=new ArrayList<>();
        arrayList.add(new file_list("1","crc file.pdf"));
        arrayList.add(new file_list("1","crc file.pdf"));
        arrayList.add(new file_list("1","crc file.pdf"));

        list=(RecyclerView)view.findViewById(R.id.file_list);
        utils_adapter utils_adapter=new utils_adapter();
        utils_adapter.Adapter(list,new file_result_adapter(getActivity(),arrayList),getActivity());
    }
}
