package com.besolutions.konsil.scenarios.scenarios_faq.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenarios_faq.model.Datum;
import com.besolutions.konsil.scenarios.scenarios_faq.model.root_faq;
import com.besolutions.konsil.scenarios.scenarios_faq.pattern.expandAdapter;
import com.besolutions.konsil.scenarios.scenarios_faq.pattern.expandlist;
import com.besolutions.konsil.scenarios.scenarios_faq.model.itemList;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;

public class faq extends AppCompatActivity implements NetworkInterface {

    root_faq root_faq;
    Datum[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);

        try {
            new Apicalls(this, this).faq();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        set_toolbar_name();

        try {
            new Apicalls(this,this).faq();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void set_toolbar_name() {
        Toolbar mToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title = findViewById(R.id.title);
        title.setText(getResources().getString(R.string.faq));
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        RecyclerView recyclerViewexpand = findViewById(R.id.recycle);

        ArrayList<expandlist> mylsit = new ArrayList();

        Gson gson = new Gson();
        root_faq = gson.fromJson("" + model.getJsonObject(), root_faq.class);

        data = root_faq.getData();

        for(int index = 0 ; index < data.length ; index++ )
        {
            ArrayList<itemList> about =  new ArrayList();
            about.add(new itemList(data[index].getId(),data[index].getAnswer()));

            mylsit.add(new expandlist(data[index].getId(), data[index].getQuestion(), about));

        }

        RecyclerView.LayoutManager layoutManagerr = new LinearLayoutManager(this);
        recyclerViewexpand.setLayoutManager(layoutManagerr);
        RecyclerView.Adapter adapterr = new expandAdapter(mylsit, faq.this);
        recyclerViewexpand.setAdapter(adapterr);
    }

    @Override
    public void OnError(VolleyError error) {

    }
}
