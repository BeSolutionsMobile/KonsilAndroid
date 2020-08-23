package com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.report.Controller;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.model.Consultation;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.model.root_download_report;
import com.besolutions.konsil.scenarios.scenario_make_complaint.Controller.make_a_complaint;
import com.besolutions.konsil.scenarios.scenario_my_consultations.pattern.my_consultations_adapter;
import com.google.gson.Gson;

import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_report_online_c extends Fragment implements View.OnClickListener, NetworkInterface {

    ImageView download;
    root_download_report root_download_report;
    Consultation consultations;
    TextView date, status;
    TextView no_medical;
    LinearLayout setvisablitiy;
    String no_med;

    public fragment_report_online_c() {
        // Required empty public constructor
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_report_online_c, container, false);
        Button make_complaint = view.findViewById(R.id.make_complaint);
        download = view.findViewById(R.id.download);
        setvisablitiy = view.findViewById(R.id.setvisablitiy);
        no_medical = view.findViewById(R.id.no_medical);

        download.setOnClickListener(this);
        make_complaint.setOnClickListener(this);

        //CALL SERVER
        try {
            new Apicalls(getContext(), fragment_report_online_c.this).download_file(my_consultations_adapter.id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        no_med = getResources().getString(R.string.no_medical_r);


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

            no_medical.setText(no_med);
        } else {
            //SET VISIBILITY AND TEXT
            setvisablitiy.setVisibility(View.VISIBLE);
            no_medical.setText("");
        }

    }

    @Override
    public void OnError(VolleyError error) {

    }
}
