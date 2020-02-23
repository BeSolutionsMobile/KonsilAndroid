package com.besolutions.konsil.scenarios.scenario_my_consultations.Controlller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_my_consultations.model.Datum;
import com.besolutions.konsil.scenarios.scenario_my_consultations.model.root_consultation;
import com.besolutions.konsil.scenarios.scenario_my_consultations.pattern.my_consultations_adapter;
import com.besolutions.konsil.scenarios.scenario_my_consultations.model.my_consultations_list;
import com.besolutions.konsil.utils.utils_adapter;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;

public class my_consultations extends AppCompatActivity implements NetworkInterface {
    RecyclerView my_consitutauin_list;

    root_consultation root_consultation;

    Datum[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_consultations);

        set_toolbar_name();

        //GET DATA FROM SERVER
        try {
            new Apicalls(this,this).my_consultations();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void set_toolbar_name() {
        Toolbar mToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title = findViewById(R.id.title);
        String my_consultation = getResources().getString(R.string.my_consultation);

        title.setText(my_consultation);
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        Log.e("model_data",model.getJsonObject()+"");

        ArrayList<my_consultations_list> arrayList = new ArrayList<>();

        my_consitutauin_list = findViewById(R.id.my_consitutauin_list);

        Gson gson = new Gson();
        root_consultation = gson.fromJson(""+model.getJsonObject(),root_consultation.class);

        data = root_consultation.getData();

        //LOOP ON ALL DATA
        for(int index = 0; index <data.length ; index++)
        {
            arrayList.add(new my_consultations_list(data[index].getName(), data[index].getStatus(), (String) data[index].getPrice(), data[index].getStatus(), data[index].getImage(), ""+data[index].getId(),data[index].getType(),""+data[index].getDocId()));
        }

        utils_adapter utils_adapter = new utils_adapter();
        utils_adapter.Adapter(my_consitutauin_list, new my_consultations_adapter(this, arrayList), this);

    }

    @Override
    public void OnError(VolleyError error) {

    }
}
