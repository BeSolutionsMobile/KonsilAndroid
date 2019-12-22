package com.besolutions.konsil.scenarios.scenario_request_online_conversation.pattern;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.model.conversation_reserv_list;

import java.util.ArrayList;

public class conversation_item_adapter extends RecyclerView.Adapter<conversation_item_adapter.conversation_holder> {
 Context context;
 ArrayList<conversation_reserv_list> mylist;

    public conversation_item_adapter(Context context, ArrayList<conversation_reserv_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public conversation_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.converstaion_item,viewGroup,false);
        conversation_holder conversation_holder=new conversation_holder(view);
        return conversation_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull conversation_holder viewHolder, int i) {
       viewHolder.radio_txt.setText(mylist.get(i).getResev_txt());
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class  conversation_holder extends RecyclerView.ViewHolder {
        RadioButton radio_txt;
        public conversation_holder(@NonNull View itemView) {
            super(itemView);
            radio_txt=(RadioButton)itemView.findViewById(R.id.radio_item);
        }
    }
}
