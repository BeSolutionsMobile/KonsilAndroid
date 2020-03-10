package com.besolutions.konsil.scenarios.scenario_doctor_list.pattern;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_doctor_info.Controller.doctor_info;
import com.besolutions.konsil.scenarios.scenario_doctor_list.model.doctor_list_items;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class doctor_adapter extends RecyclerView.Adapter<doctor_adapter.doctor_holder> {
    ArrayList<doctor_list_items> mylist;
    Context context;

    public doctor_adapter(ArrayList<doctor_list_items> mylist, Context context) {
        this.mylist = mylist;
        this.context = context;
    }

    @NonNull
    @Override
    public doctor_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.doctor_item, viewGroup, false);
        doctor_holder doctor_holder = new doctor_holder(view);
        return doctor_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull doctor_holder viewHolder, final int i) {
        Glide.with(context).load(mylist.get(i).getImg()).into(viewHolder.doctor_img);
        viewHolder.name.setText(mylist.get(i).getName());
        viewHolder.degree.setText(mylist.get(i).getDegree());
        viewHolder.rating.setRating(mylist.get(i).getRate());
        viewHolder.lang.setText(mylist.get(i).getLang());
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, doctor_info.class);
                intent.putExtra("id", mylist.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class doctor_holder extends RecyclerView.ViewHolder {
        TextView name, degree, lang;
        ImageView doctor_img;
        RatingBar rating;
        LinearLayout item;

        public doctor_holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            degree = itemView.findViewById(R.id.degree);
            doctor_img = itemView.findViewById(R.id.doctor_img);
            rating = itemView.findViewById(R.id.ratings);
            item = itemView.findViewById(R.id.item);
            lang = itemView.findViewById(R.id.lang);
        }
    }

}


