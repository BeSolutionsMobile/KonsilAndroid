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
import com.besolutions.konsil.scenarios.scenario_doctor_info.Controller.doctor_info;
import com.besolutions.konsil.scenarios.scenario_my_consultations.model.my_consultations_list;

import java.util.ArrayList;

public class my_consultations_adapter extends RecyclerView.Adapter<my_consultations_adapter.my_consultations_holder> {
    Context context;
    ArrayList<my_consultations_list>mylist;

    public my_consultations_adapter(Context context, ArrayList<my_consultations_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public my_consultations_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.my_consulatation_item,viewGroup,false);
        my_consultations_holder my_consultations_holder=new my_consultations_holder(view);
        return my_consultations_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull my_consultations_holder viewHolder, int i) {
      viewHolder.name.setText(mylist.get(i).getName().toString());
      viewHolder.desc.setText(mylist.get(i).getDesc().toString());
      viewHolder.price.setText(mylist.get(i).getPrice().toString());
      viewHolder.status.setText(mylist.get(i).getStatus().toString());
      viewHolder.item.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
         //  context.startActivity(new Intent(context, doctor_info.class));
          }
      });
      viewHolder.view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              context.startActivity(new Intent(context, doctor_info.class));
          }
      });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class my_consultations_holder extends RecyclerView.ViewHolder {
        TextView name,desc,price,status;
        Button view;
        LinearLayout item;
        public my_consultations_holder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            desc=(TextView)itemView.findViewById(R.id.desc);
            price=(TextView)itemView.findViewById(R.id.price);
            status=(TextView)itemView.findViewById(R.id.status);
            item=(LinearLayout)itemView.findViewById(R.id.item);
            view=(Button)itemView.findViewById(R.id.view);
        }
    }
}
