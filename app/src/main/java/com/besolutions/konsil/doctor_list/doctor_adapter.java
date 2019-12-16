package com.besolutions.konsil.doctor_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.besolutions.konsil.R;

import java.util.ArrayList;

public class doctor_adapter extends RecyclerView.Adapter<doctor_adapter.doctor_holder> {
    ArrayList<doctor_list_items>mylist;
    Context context;

    public doctor_adapter(ArrayList<doctor_list_items> mylist, Context context) {
        this.mylist = mylist;
        this.context = context;
    }

    @NonNull
    @Override
    public doctor_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.doctor_item,viewGroup,false);
        doctor_holder doctor_holder=new doctor_holder(view);
        return doctor_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull doctor_holder viewHolder, int i) {
    viewHolder.name.setText(mylist.get(i).getName().toString());
    viewHolder.degree.setText(mylist.get(i).getDegree().toString());
    viewHolder.rating.setRating(mylist.get(i).getRate());
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }
    class doctor_holder extends RecyclerView.ViewHolder {
        TextView name,degree;
        ImageView doctor_img;
        RatingBar rating;
        public doctor_holder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            degree=(TextView)itemView.findViewById(R.id.degree);
            doctor_img=(ImageView)itemView.findViewById(R.id.doctor_img);
            rating=(RatingBar)itemView.findViewById(R.id.ratings);
        }
    }

}


