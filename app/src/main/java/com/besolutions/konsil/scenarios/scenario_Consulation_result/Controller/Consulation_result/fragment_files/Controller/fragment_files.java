package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_files.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_files.model.root_download_files;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.model.file_list;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.file_result_adapter;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_my_consultations.pattern.my_consultations_adapter;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.Controller.request_online_conversation;
import com.besolutions.konsil.utils.utils_adapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
    Button button;
    ImageView upload_files;
    root_download_files root_download_files;

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


        return view;
    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {


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


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void OnError(VolleyError error) {

    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
