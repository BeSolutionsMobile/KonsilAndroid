package com.besolutions.konsil.scenarios.scenario_request_online_conversation.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.online_conversation;
import com.besolutions.konsil.scenarios.scenario_payment.controller.payment;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.model.Datum;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.model.root_appointment;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.pattern.conversation_item_adapter;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.model.conversation_reserv_list;
import com.besolutions.konsil.utils.utils_adapter;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;

public class request_online_conversation extends AppCompatActivity implements View.OnClickListener, NetworkInterface {
    RecyclerView reserv_list;
    Button complete_req;
    Datum[] datnum;
    RatingBar ratingBar;
    de.hdodenhof.circleimageview.CircleImageView ci_image;
    TextView doc_name,job_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_online_conversation);
        complete_req = findViewById(R.id.complete_req);
        complete_req.setOnClickListener(this);

        try {
            new Apicalls(this, this).appointment(String.valueOf(43), "2020-02-13");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        set_toolbar_name();

        //SET DATA
        set_doc_details();


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.complete_req) {
            startActivity(new Intent(this, payment.class));
        }
    }

    public void set_toolbar_name() {
        Toolbar mToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title = findViewById(R.id.title);
        String r_o_c = getResources().getString(R.string.request_online_converstaion);
        title.setText(r_o_c);
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        Gson gson = new Gson();
        root_appointment root_appointment = gson.fromJson(""+model.getJsonObject(), root_appointment.class);
        datnum = root_appointment.getData();

        reserv_list = findViewById(R.id.reserv_list);

        ArrayList<conversation_reserv_list> arrayList = new ArrayList<>();

        for (int index = 0; index < datnum.length; index++) {
            arrayList.add(new conversation_reserv_list("" + datnum[index].getId(), datnum[index].getTime()));
        }
        utils_adapter utils_adapter = new utils_adapter();
        utils_adapter.Adapter(reserv_list, new conversation_item_adapter(this, arrayList), this);

    }

    @Override
    public void OnError(VolleyError error) {
        Toast.makeText(this, "" + error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();
    }

    //SET DOCTOR_DETAILS
    void set_doc_details()
    {
        // GET DATA FROM PREVIOUS PAGE
        String image = getIntent().getStringExtra("image");
        String docName_ = getIntent().getStringExtra("doc_name");
        String jobTitle = getIntent().getStringExtra("doc_title");
        int rating_bar = getIntent().getIntExtra("rating",0);

        ci_image = findViewById(R.id.doc_img);
        doc_name = findViewById(R.id.doc_name);
        job_title = findViewById(R.id.job_title);
        ratingBar = findViewById(R.id.ratings);

        //SET DATA


    }
}
