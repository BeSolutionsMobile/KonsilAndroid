package com.besolutions.konsil.scenarios.scenario_my_consultations.pattern;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.consulation_result;
import com.besolutions.konsil.scenarios.scenario_doctor_info.Controller.doctor_info;
import com.besolutions.konsil.scenarios.scenario_my_consultations.model.my_consultations_list;
import com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.online_conversation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class my_consultations_adapter extends RecyclerView.Adapter<my_consultations_adapter.my_consultations_holder> {
    Context context;
    ArrayList<my_consultations_list> mylist;
    public static String id;
    public static String doc_id;

    public my_consultations_adapter(Context context, ArrayList<my_consultations_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public my_consultations_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_consulatation_item, viewGroup, false);
        my_consultations_holder my_consultations_holder = new my_consultations_holder(view);
        return my_consultations_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull my_consultations_holder viewHolder, final int i) {

        //DEFINE ALL VARS
        Picasso.with(context).load(mylist.get(i).getImg()).into(viewHolder.circleImageView);
        viewHolder.name.setText(mylist.get(i).getName());
        viewHolder.price.setText(mylist.get(i).getPrice());
        viewHolder.status.setText(mylist.get(i).getStatus());

        //CHECK TYPE TO KNOW IF VIEW IS CONSULTATION OR CONVERSATION
        if(mylist.get(i).getType().equals("1"))
        {
            viewHolder.desc.setText("consultation");   //SET TEXT CONSULTATION
        }
        else {
            viewHolder.desc.setText("conversation");   //SET TEXT CONVERSATION
        }

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CHECK FOR TYPE TO KNOW GO TO CONVERSATION OR CONSULTATION
                if (mylist.get(i).getType().equals("1")) {        //CONSULTATION

                    doc_id = mylist.get(i).getDoc_id();
                    Intent intent = new Intent(context, consulation_result.class);
                    id = mylist.get(i).getId();
                    context.startActivity(intent);

                } else if (mylist.get(i).getType().equals("2")) {     //CONVERSATION

                    doc_id = mylist.get(i).getDoc_id();
                    Intent intent = new Intent(context, online_conversation.class);
                    id = mylist.get(i).getId();
                    context.startActivity(intent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class my_consultations_holder extends RecyclerView.ViewHolder {
        TextView name, desc, price, status;
        Button view;
        LinearLayout item;
        de.hdodenhof.circleimageview.CircleImageView circleImageView;

        public my_consultations_holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            price = itemView.findViewById(R.id.price);
            status = itemView.findViewById(R.id.status);
            item = itemView.findViewById(R.id.item);
            view = itemView.findViewById(R.id.view);
            circleImageView = itemView.findViewById(R.id.doc_img);
        }
    }
}
