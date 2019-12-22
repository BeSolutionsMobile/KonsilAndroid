package com.besolutions.konsil.scenarios.scenario_mian_page.pattern;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.doctor_list;
import com.besolutions.konsil.scenarios.scenario_mian_page.model.main_screen_list;

import java.util.ArrayList;

public class main_screen_adapter extends RecyclerView.Adapter<main_screen_adapter.main_screen_holder> {
    Context context;
    ArrayList<main_screen_list>mylist;

    public main_screen_adapter(Context context, ArrayList<main_screen_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public main_screen_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.f_spechial_item,viewGroup,false);
        main_screen_holder main_screen_holder=new main_screen_holder(view);
        return main_screen_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull main_screen_holder viewHolder, int i) {
      viewHolder.name.setText(mylist.get(i).getName());

      viewHolder.item.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              context.startActivity(new Intent(context, doctor_list.class));
          }
      });

      if(i==0)
      {
          viewHolder.item.setBackgroundResource(R.drawable.f_special_bg);
      }
      else if(i==1)
      {
          viewHolder.item.setBackgroundResource(R.drawable.s_spechial_bg);
      }
      else if(i == mylist.size()-1)
      {
          viewHolder.item.setBackgroundResource(R.drawable.t_special_bg);
      }
      else if(i==mylist.size()-2)
      {
          viewHolder.item.setBackgroundResource(R.drawable.f_spechail_bg);
      }
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class main_screen_holder extends RecyclerView.ViewHolder {
        LinearLayout item;
        TextView name;
        public main_screen_holder(@NonNull View itemView) {
            super(itemView);
            item=(LinearLayout)itemView.findViewById(R.id.item);
            name=(TextView)itemView.findViewById(R.id.name);
        }
    }
}
