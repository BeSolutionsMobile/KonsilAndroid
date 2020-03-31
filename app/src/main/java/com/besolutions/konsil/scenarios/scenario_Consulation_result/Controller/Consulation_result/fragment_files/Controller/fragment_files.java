package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_files.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.model.file_list;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.file_result_adapter;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_my_consultations.pattern.my_consultations_adapter;
import com.besolutions.konsil.utils.utils_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_files extends Fragment implements NetworkInterface,View.OnClickListener {
    View view;
    RecyclerView list;
    ProgressBar pg;
    TextView nodata,files;

    public fragment_files() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_files, container, false);

        //CALL SERVER
        try {
            new Apicalls(getContext(), this).consultation_files(my_consultations_adapter.id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //DEFINE VARS
        pg = view.findViewById(R.id.pg);
        nodata = view.findViewById(R.id.files);
        files = view.findViewById(R.id.status);

        files.setText(my_consultations_adapter.status);
        return view;
    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        pg.setVisibility(View.GONE); //SET IT IF THERE IS DATA FROM SERVER

        ArrayList<file_list> arrayList = new ArrayList<>();

        JSONObject jsonObject = model.getJsonObject();
        try {
            JSONObject jsonObject1 = jsonObject.getJSONObject("consultation");
            JSONArray jsonArray = jsonObject1.getJSONArray("files");

            for (int index = 0; index < jsonArray.length(); index++) {

                String jsonObject2 = jsonArray.getString(index);
                arrayList.add(new file_list("1",jsonObject2 ));

            }

            list = view.findViewById(R.id.file_list);
            utils_adapter utils_adapter = new utils_adapter();
            utils_adapter.Adapter(list, new file_result_adapter(getActivity(), arrayList), getActivity());

            //CHECK IF THERE IS NO FILE UPLOADED THEN CHANGE THE TEXT
            if(jsonArray.length() == 0 )
            {
                String files = getActivity().getString(R.string.no_files);
                nodata.setText(files);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE); //SET IT IF THERE IS DATA FROM SERVER
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
