package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.Controller;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.model.Consultation;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.model.root_download_report;
import com.besolutions.konsil.scenarios.scenario_make_complaint.Controller.make_a_complaint;
import com.besolutions.konsil.scenarios.scenario_my_consultations.pattern.my_consultations_adapter;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.Controller.request_online_conversation;
import com.google.gson.Gson;

import org.json.JSONException;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_report extends Fragment implements View.OnClickListener, NetworkInterface {
    RecyclerView list;
    View view;
    ImageView download;
    root_download_report root_download_report;
    Consultation consultations;
    TextView no_medical;
    LinearLayout setvisablitiy;

    public fragment_report() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_report, container, false);

        Button make_a_complaint = view.findViewById(R.id.make_complaint);
        download = view.findViewById(R.id.download);
        setvisablitiy = view.findViewById(R.id.setvisablitiy);
        no_medical = view.findViewById(R.id.no_medical);

        make_a_complaint.setOnClickListener(this);
        download.setOnClickListener(this);

        try {
            new Apicalls(getContext(), this).download_file(my_consultations_adapter.id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return view;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.make_complaint) {
            startActivity(new Intent(getActivity(), make_a_complaint.class));
        } else if (v.getId() == R.id.download) {
            //GO TO URL
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(consultations.getUrl()));
            startActivity(browse);
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        Gson gson = new Gson();
        root_download_report = gson.fromJson("" + model.getJsonObject(), root_download_report.class);

        consultations = root_download_report.getConsultation();

        if (consultations.getUrl().equals("noLink")) {

            //SET VISIBILITY AND TEXT
            setvisablitiy.setVisibility(View.GONE);

            String no_medical_r = getResources().getString(R.string.no_medical_r);
            no_medical.setText(no_medical_r);
        } else {

            //SET VISIBILITY AND TEXT
            setvisablitiy.setVisibility(View.VISIBLE);
            no_medical.setText("");
        }


    }

    @Override
    public void OnError(VolleyError error) {
        Toast.makeText(getActivity(), "" + error.networkResponse, Toast.LENGTH_SHORT).show();
    }
}
