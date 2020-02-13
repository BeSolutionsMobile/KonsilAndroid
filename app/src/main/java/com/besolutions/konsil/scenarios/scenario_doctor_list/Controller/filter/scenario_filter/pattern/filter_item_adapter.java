package com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.scenario_filter.pattern;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.scenario_filter.model.filter_reserv_list;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.model.conversation_reserv_list;

import java.util.ArrayList;

public class filter_item_adapter extends RecyclerView.Adapter<filter_item_adapter.conversation_holder> {

       private Context context;
       private ArrayList<filter_reserv_list> mylist;

    private RadioButton lastCheckedRB = null;

    public filter_item_adapter(Context context, ArrayList<filter_reserv_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public conversation_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.converstaion_item,viewGroup,false);
        return new conversation_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final conversation_holder viewHolder, final int position) {

        viewHolder.radio_txt.setText(mylist.get(position).getResev_txt());

        viewHolder.radio_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checked_rb = (RadioButton) v;
                if(lastCheckedRB != null){
                    lastCheckedRB.setChecked(false);
                }
                lastCheckedRB = checked_rb;

            }
        });

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class  conversation_holder extends RecyclerView.ViewHolder {
        RadioButton radio_txt;
        conversation_holder(@NonNull View itemView) {
            super(itemView);
            radio_txt= itemView.findViewById(R.id.radio_item);
        }
    }
}