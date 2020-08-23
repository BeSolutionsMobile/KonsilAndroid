package com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.info.Controller;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_my_consultations.pattern.my_consultations_adapter;
import com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.info.model.Datum;
import com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.info.model.root_conversation_details;
import com.google.gson.Gson;

import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_info extends Fragment implements NetworkInterface {

    View view;
    TextView doc_name, date, con_link, status;
    root_conversation_details root_conversation_details;
    Datum data;

    public static String date_conv ;
    public static String status_conv ;

    public fragment_info() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_info, container, false);

        //DEFINE ALL VARS
        doc_name = view.findViewById(R.id.doc_name);
        date = view.findViewById(R.id.date);
        con_link = view.findViewById(R.id.con_link);
        status = view.findViewById(R.id.status);

        try {
            new Apicalls(getActivity(), fragment_info.this).confirm_conversation(my_consultations_adapter.id, "1","0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        Gson gson = new Gson();
        root_conversation_details = gson.fromJson(""+model.getJsonObject() , root_conversation_details.class);

        data = root_conversation_details.getData();

        //SET ALL DATA IN VARS
        doc_name.setText(data.getDoctor());
        date.setText(data.getDate());
        con_link.setText(data.getConversationLink());
        status.setText(data.getStatus());

        //SET DATA TO DATE AND STATUS TO MAKE IT REUSABLE
        date_conv = data.getDate();
        status_conv = data.getStatus();

       // Toast.makeText(getActivity(), ""+date_conv+"////"+status_conv, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnError(VolleyError error) {

    }
}
