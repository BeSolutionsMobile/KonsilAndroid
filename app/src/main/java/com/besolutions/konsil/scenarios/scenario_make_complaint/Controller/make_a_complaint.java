package com.besolutions.konsil.scenarios.scenario_make_complaint.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.network_check_status.regist_network_broadcast;
import com.besolutions.konsil.scenarios.scenario_compalint_details.compalint_details.Controller.compalint_details;
import com.besolutions.konsil.scenarios.scenario_make_complaint.model.Datum;
import com.besolutions.konsil.scenarios.scenario_make_complaint.model.root_complaint_type;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class make_a_complaint extends AppCompatActivity implements View.OnClickListener, NetworkInterface {
    Button submit;
    Spinner make_complaint_spi;
    root_complaint_type root_complaint_type;
    Datum[] data;
    EditText enter_msg;
    int flag = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_a_complaint);
        submit = (Button) findViewById(R.id.make_complaint);
        make_complaint_spi = (Spinner) findViewById(R.id.make_complaint_spi);

        enter_msg = findViewById(R.id.enter_msg);

        submit.setOnClickListener(this);

        TextView title = (TextView) findViewById(R.id.title);
        String make_complaint = getResources().getString(R.string.make_a_complaint);
        title.setText(make_complaint);

        //GET ALL TYPES
        try {
            new Apicalls(this, this).get_complaint_type();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Set_Spinner_id();

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(make_a_complaint.this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.make_complaint) {
            try {
                new Apicalls(make_a_complaint.this, make_a_complaint.this).make_complaint(Set_Spinner_id(),enter_msg.getText().toString());
                flag = 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        //CHECK FOR FLAG IF ITS 0 OR 1 TO KNOW SEND DATA OR GET DATA
        if(flag == 0 ) {
            Gson gson = new Gson();
            root_complaint_type = gson.fromJson("" + model.getJsonObject(), root_complaint_type.class);

            data = root_complaint_type.getData();

            Log.e("complaintt", "" + data[0].getTitle());


            ArrayList<String> arrayList = new ArrayList<>();


            for (int index = 0; index < data.length; index++) {
                arrayList.add(String.valueOf(data[index].getTitle()));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, arrayList);

            Log.e("alldatrain", arrayList.get(0));
            make_complaint_spi.setAdapter(adapter);
        }

        else if(flag == 1 )
        {
            String com_sent = getResources().getString(R.string.com_sent);
            Toasty.success(make_a_complaint.this,com_sent,Toasty.LENGTH_LONG).show();
            flag = 0;

            //GO TO MAIN SCREEN
            startActivity(new Intent(make_a_complaint.this,main_screen.class));

        }

    }

    @Override
    public void OnError(VolleyError error) {

    }

    String ids;

    String Set_Spinner_id() {

        //SET ON SPINNER TO GET ID
        make_complaint_spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ids = String.valueOf(data[position].getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return ids;
    }
}
