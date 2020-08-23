package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.pattern;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.model.consulation_list;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class consulation_result_adapter extends RecyclerView.Adapter<consulation_result_adapter.consulation_result_holder> {
    Context context;
    ArrayList<consulation_list>mylist;

    public consulation_result_adapter(Context context, ArrayList<consulation_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public consulation_result_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.message_iten,viewGroup,false);
        consulation_result_holder consulation_result_holder=new consulation_result_holder(view);
        return  consulation_result_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull consulation_result_holder viewHolder, int i) {
     viewHolder.name.setText(mylist.get(i).getName());
     viewHolder.desc.setText(mylist.get(i).getDesc());

     if(mylist.get(i).getImg().toString().equals("noImage"))
     {
         viewHolder.img.setImageResource(R.drawable.konzil_logo);
     }
     else {
         Glide.with(context).load(mylist.get(i).getImg()).into(viewHolder.img);
     }

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class consulation_result_holder extends RecyclerView.ViewHolder {
        TextView name,desc;
        de.hdodenhof.circleimageview.CircleImageView img;
        public consulation_result_holder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name);
            desc= itemView.findViewById(R.id.desc);
            img= itemView.findViewById(R.id.img);
        }
    }
}
