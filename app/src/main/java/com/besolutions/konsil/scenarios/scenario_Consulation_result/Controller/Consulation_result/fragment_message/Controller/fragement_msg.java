package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
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

    public fragement_msg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_msg, container, false);

        //GET MESSAGES FROM SERVER
        try {
            new Apicalls(getActivity(), fragement_msg.this).get_all_msg(my_consultations_adapter.id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //DEFINE ALL VARS
        enter_msg = view.findViewById(R.id.enter_msg);
        send_msg = view.findViewById(R.id.send_msg);
        nomsg = view.findViewById(R.id.nomsg);

        Button req_online_conv = view.findViewById(R.id.req_online_conv);
        req_online_conv.setOnClickListener(this);

        //SET ON CLICK BUTTON
        send_msg.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.req_online_conv) {
            startActivity(new Intent(getActivity(), request_online_conversation.class));
        } else if (v.getId() == R.id.send_msg) {
            try {
                new Apicalls(getActivity(), this).send_msg(my_consultations_adapter.id, enter_msg.getText().toString());
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

        //GET DATA FROM SERVER GET ALL MESSAGES
        if (msg_status == 0) {
            Gson gson = new Gson();
            root_msg root_msg = gson.fromJson("" + model.getJsonObject(), root_msg.class);

            msg_list = view.findViewById(R.id.msg_list);
            ArrayList<consulation_list> arrayList = new ArrayList<>();

            messages = root_msg.getMessages();
            if (messages.length == 0) {
                nomsg.setText("لاتوجد رسائل");
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
            Intent intent =new Intent(getContext(), consulation_result.class);
            getActivity().finish();
            startActivity(intent);
            msg_status = 0;

        }

    }

    @Override
    public void OnError(VolleyError error) {

    }

}
