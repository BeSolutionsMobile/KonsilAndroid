package com.besolutions.konsil.scenarios.scenario_doctor_list.Controller;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.scenario_filter.Controller.filter;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.scenario_filter.pattern.filter_item_adapter;
import com.besolutions.konsil.scenarios.scenario_doctor_list.model.Degree;
import com.besolutions.konsil.scenarios.scenario_doctor_list.model.Doctor;
import com.besolutions.konsil.scenarios.scenario_doctor_list.model.doctor_list_items;
import com.besolutions.konsil.scenarios.scenario_doctor_list.model.root;
import com.besolutions.konsil.scenarios.scenario_doctor_list.pattern.doctor_adapter;
import com.besolutions.konsil.utils.utils_adapter;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;

public class doctor_list extends AppCompatActivity implements View.OnClickListener, NetworkInterface {
    RecyclerView doctor_list;
    TextView filter;
    Doctor[] doctor;
    Degree[] Degree;
    ResponseModel model;
    root root_doc_speciality;
    ProgressBar pg;
    static int id;
    static int id_filter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list);

        filter = findViewById(R.id.filter);
        filter.setOnClickListener(this);

        Toolbar mToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        pg = findViewById(R.id.pg);

        TextView title = findViewById(R.id.title);
        String doc_filters = getResources().getString(R.string.Specialist);
        title.setText(doc_filters);

        id = getIntent().getIntExtra("id", 0);
        int num = getIntent().getIntExtra("num", 0);

        //GET DATA FROM SERVER
        try {
            if (num == 0) {
                int stars_num = getIntent().getIntExtra("stars_num", 0);
                new Apicalls(this, this).FILTER("" + id_filter, filter_item_adapter.int_list, String.valueOf(stars_num));
            } else {
                id_filter = id;
                new Apicalls(this, this).doctor_speciality(id);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.filter) {
            com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.scenario_filter.Controller.filter filter = new filter();
            filter.dialog(this, R.layout.doctor_filter, .90, Degree);
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        //PROGRESS BAR VISABILITY
        pg.setVisibility(View.GONE);

        this.model = model;

        doctor_list = findViewById(R.id.doctor_list);
        ArrayList<doctor_list_items> arrayList = new ArrayList<>();

        Gson gson = new Gson();
        root_doc_speciality = gson.fromJson("" + model.getJsonObject(), root.class);

        doctor = root_doc_speciality.getDoctors();
        Degree = root_doc_speciality.getDegrees();

        TextView nodata = findViewById(R.id.nodata);
        //CHECK IF WE FOUND DOCTOR DATA OR NOT
        if (root_doc_speciality.getDoctors().length == 0) {
            String no_doctors = getResources().getString(R.string.no_doctors);
            nodata.setText(no_doctors);
        } else {
            nodata.setText("");
        }

        Log.e("imageeeeee",""+root_doc_speciality.getDoctors().length);


        for (int index = 0; index < root_doc_speciality.getDoctors().length; index++) {
            String image_url = doctor[index].getImageUrl();
            image_url.replace(" ","20%");
            arrayList.add(new doctor_list_items("" + doctor[index].getId(), doctor[index].getName(), doctor[index].getDegree(), image_url, doctor[index].getRate(),doctor[index].getLang()));
        }

        utils_adapter utils_adapter = new utils_adapter();
        utils_adapter.Adapter(doctor_list, new doctor_adapter(arrayList, this), this);
    }

    @Override
    public void OnError(VolleyError error) {
        Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
    }


}
