package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.consulation_result;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.model.Message;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.model.consulation_list;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.model.root_msg;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.pattern.consulation_result_adapter;
import com.besolutions.konsil.scenarios.scenario_my_consultations.pattern.my_consultations_adapter;
import com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.online_conversation;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.Controller.request_online_conversation;
import com.besolutions.konsil.utils.utils;
import com.besolutions.konsil.utils.utils_adapter;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragement_msg extends Fragment implements View.OnClickListener, NetworkInterface {
    RecyclerView msg_list;
    View view;
    EditText enter_msg;
    Button send_msg;
    int msg_status = 0;
    Message[] messages;
    TextView nomsg;
    ProgressBar pg;
    RelativeLayout msg_container;
    String no_msg;
    public fragement_msg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_msg, container, false);

        //METHOD TO GET DATA FROM SERVER
        get_all_msg();

        //DEFINE ALL VARS
        enter_msg = view.findViewById(R.id.enter_msg);
        send_msg = view.findViewById(R.id.send_msg);
        nomsg = view.findViewById(R.id.nomsg);
        pg = view.findViewById(R.id.pg);
        msg_container = view.findViewById(R.id.msg_container);

         no_msg = getActivity().getResources().getString(R.string.no_msg); //THERE IS NO MESSAGE FOUND

         String closed_cons = getActivity().getResources().getString(R.string.closed_cons);

        //SET ON CLICK BUTTON
        send_msg.setOnClickListener(this);

        //CHECK STATUS TO MAKE HIM CAN SEND MSG OR NOT
        if(my_consultations_adapter.status.equals(closed_cons))
        {
            msg_container.setVisibility(View.GONE);
        }

        swipe_refresh();

        return view;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_msg) {
            if (enter_msg.getText().length() < 3) {
                String short_msg = getResources().getString(R.string.short_msg);
                Toasty.warning(getActivity(), short_msg,Toasty.LENGTH_SHORT).show();
            } else {
                pg.setVisibility(View.VISIBLE); // SET PROGREES BAR VISIBLITY

                try {
                    new Apicalls(getActivity(), this).send_msg(my_consultations_adapter.id, enter_msg.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {



        //GET DATA FROM SERVER GET ALL MESSAGES
        if (msg_status == 0) {

            //SET VISABILITY GONE
            pg.setVisibility(View.GONE);

            Gson gson = new Gson();
            root_msg root_msg = gson.fromJson("" + model.getJsonObject(), root_msg.class);

            msg_list = view.findViewById(R.id.msg_list);
            ArrayList<consulation_list> arrayList = new ArrayList<>();

            messages = root_msg.getMessages();
            if (messages.length == 0) {
                nomsg.setText(no_msg);
            } else {
                for (int index = 0; index < messages.length; index++) {
                    arrayList.add(new consulation_list("" + messages[index].getId(), messages[index].getName(), messages[index].getMessage(), messages[index].getUserImage()));
                }

                utils_adapter utils_adapter = new utils_adapter();
                utils_adapter.Adapter(msg_list, new consulation_result_adapter(getActivity(), arrayList), getActivity());

            }

            // SET MSG STATUS = 1
            msg_status = 1;
        }

        //SEND DATA TO SERVER SEND MSG

        else if (msg_status == 1) {
            pg.setVisibility(View.VISIBLE); // SET PROGREES BAR GONE
            get_all_msg();   //METHOD FROM SERVER
            msg_status = 0;    // SET STATE ZERO TO KONW TYPE IF GET MESSAGE OR SEND MESSAGE
            enter_msg.getText().clear();    // SET EDIT TEXT EMPTY AFTER SEND MSG
            nomsg.setText("");    //SET TEXT EMPTY IF LENGTH MORE THAN ONE

        }

    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE); //SET IT IF THERE IS DATA FROM SERVER

    }

    //GET MESSAGES FROM SERVER
    void get_all_msg() {
        try {
            new Apicalls(getActivity(), fragement_msg.this).get_all_msg(my_consultations_adapter.id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //SWIPE REFRERSH
    void swipe_refresh()
    {
        /// swipe Refresh
        final SwipeRefreshLayout swipNotif = view.findViewById(R.id.swipe);
        swipNotif.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                get_all_msg();
                swipNotif.setRefreshing(false);
            }
        });
    }


}
