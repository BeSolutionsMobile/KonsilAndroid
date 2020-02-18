package com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.scenario_filter.pattern;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.scenario_filter.model.filter_reserv_list;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.model.conversation_reserv_list;

import java.util.ArrayList;
import java.util.List;

public class filter_item_adapter extends RecyclerView.Adapter<filter_item_adapter.conversation_holder> {

       private Context context;
       private ArrayList<filter_reserv_list> mylist;
       public static List<Integer> int_list;

    private RadioButton lastCheckedRB = null;

    public filter_item_adapter(Context context, ArrayList<filter_reserv_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public conversation_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.check_item,viewGroup,false);
        return new conversation_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final conversation_holder viewHolder, final int position) {

        int_list = new ArrayList<>();

        viewHolder.check_item.setText(mylist.get(position).getResev_txt());

        viewHolder.check_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked == true)
                {
                    int_list.add(Integer.valueOf(mylist.get(position).getId()));
                }
                else {
                    int_list.remove(Integer.valueOf(mylist.get(position).getId()));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class  conversation_holder extends RecyclerView.ViewHolder {
        CheckBox check_item ;
        conversation_holder(@NonNull View itemView) {
            super(itemView);
            check_item = itemView.findViewById(R.id.check_item);
        }
    }
}
