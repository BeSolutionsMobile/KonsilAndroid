package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_files.Controller;


import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.scenarios.scenario_Consulation_request.Controller.consulation_request;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_files.model.Consultation;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_files.model.root_files;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.model.file_list;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.file_result_adapter;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_my_consultations.Controlller.my_consultations;
import com.besolutions.konsil.scenarios.scenario_my_consultations.pattern.my_consultations_adapter;
import com.besolutions.konsil.utils.firebase_storage;
import com.besolutions.konsil.utils.firebase_storage_pdf;
import com.besolutions.konsil.utils.utils;
import com.besolutions.konsil.utils.utils_adapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_files extends Fragment implements NetworkInterface, View.OnClickListener {
    View view;
    RecyclerView list;
    ProgressBar pg;
    TextView nodata, files;
    LottieAnimationView upload_img_check, upload_file_check;
    ImageView upload_img, upload_file;
    String select_img, closed_cons;
    Button complete_req;
    boolean status = false;
    root_files root_files;
    Consultation[] consultation;


    String files_st;

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
        complete_req = view.findViewById(R.id.complete_req);

        //LOTTIE
        upload_img_check = view.findViewById(R.id.check_img);
        upload_file_check = view.findViewById(R.id.check_file);

        //IMAGES FILES WILL UPLAOD
        upload_img = view.findViewById(R.id.upload_img);
        upload_file = view.findViewById(R.id.upload_file);

        files.setText(my_consultations_adapter.status);

        //R.STRING TEXTS
        files_st = getActivity().getString(R.string.no_files);
        select_img = getResources().getString(R.string.select_img);
        closed_cons = getActivity().getResources().getString(R.string.closed_cons);


        //CLICK LISTNERS
        upload_img.setOnClickListener(this);
        upload_file.setOnClickListener(this);
        complete_req.setOnClickListener(this);

        return view;
    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        if (status == false) {

            pg.setVisibility(View.GONE); //SET IT IF THERE IS DATA FROM SERVER

            ArrayList<file_list> arrayList = new ArrayList<>();

            Gson gson = new Gson();

            root_files = gson.fromJson("" + model.getJsonObject(), root_files.class);

            consultation = root_files.getConsultation();

            for (int index = 0; index < consultation.length; index++) {
                arrayList.add(new file_list("" + consultation[index].getId(), consultation[index].getName(), consultation[index].getUrl()));
            }
            list = view.findViewById(R.id.file_list);
            utils_adapter utils_adapter = new utils_adapter();
            utils_adapter.Adapter(list, new file_result_adapter(getActivity(), arrayList), getActivity());


            //CHECK IF THERE IS NO FILE UPLOADED THEN CHANGE THE TEXT
            if (consultation.length == 0) {
                nodata.setText(files_st);
            }

        } else {
            String upd_sucess = getResources().getString(R.string.upd_success);
            Toasty.success(getActivity(), upd_sucess, Toasty.LENGTH_LONG).show();

            startActivity(new Intent(getActivity(), my_consultations.class));
        }

        firebase_storage.images = null;
        firebase_storage_pdf.pdf = null;
    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE); //SET IT IF THERE IS DATA FROM SERVER
    }

    @Override
    public void onClick(View v) {
        utils utils = new utils();
        if (v.getId() == R.id.upload_img) {
            //SEND ARRAY OF IMAGES
            Intent i = new Intent(Intent.EXTRA_ALLOW_MULTIPLE, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, select_img), 1);

        } else if (v.getId() == R.id.upload_file) {
            Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            i.setType("application/pdf");
            i.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(i, 2);

        } else if (v.getId() == R.id.complete_req) {
            try {
                new Apicalls(getActivity(), this).uplaod_consultation();
                status = true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                {
                    ClipData clipData = data.getClipData();
                    if (clipData == null) {

                        String select_more_imgs = getResources().getString(R.string.select_more_imgs);
                        Toasty.warning(getActivity(), select_more_imgs, Toasty.LENGTH_LONG).show();

                    } else if (clipData != null) {
                        //UPLOAD TO FIREBASE
                        firebase_storage firebase_storage = new firebase_storage();
                        firebase_storage.uploadImage(clipData, getActivity(), true);
                        upload_img_check.playAnimation();
                    }
                }
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {

                Uri selectedPdf = data.getData();

                //UPLOAD TO FIREBASE
                firebase_storage_pdf firebase_storage = new firebase_storage_pdf();
                firebase_storage.uploadImage(selectedPdf, getActivity(), true);
                upload_file_check.playAnimation();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        LinearLayout visiable = view.findViewById(R.id.linear_vis);
        if (my_consultations_adapter.status.equals(closed_cons)) {
            visiable.setVisibility(View.GONE);
            complete_req.setVisibility(View.GONE);
        }
    }
}

