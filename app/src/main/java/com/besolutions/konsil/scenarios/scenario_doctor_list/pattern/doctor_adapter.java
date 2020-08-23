package com.besolutions.konsil.scenarios.scenario_doctor_list.pattern;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
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
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;

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
    public void onBindViewHolder(@NonNull final doctor_holder viewHolder, final int i) {
        //Glide.with(context).load(mylist.get(i).getImg()).into(viewHolder.doctor_img);

        Glide.with(context)
                .load(mylist.get(i).getImg())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        viewHolder.container.stopShimmerAnimation();
                        return false;
                    }
                })
                .into(viewHolder.doctor_img);

//        viewHolder.name.setText(mylist.get(i).getName());
//        viewHolder.degree.setText(mylist.get(i).getDegree());
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

        String text = "<font color=#000000>"+mylist.get(i).getDegree()+"</font> <font color=#057493>"+" "+mylist.get(i).getName()+"</font>";
        viewHolder.name.setText(Html.fromHtml(text));
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
        ShimmerFrameLayout container;

        public doctor_holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            degree = itemView.findViewById(R.id.degree);
            doctor_img = itemView.findViewById(R.id.doctor_img);
            rating = itemView.findViewById(R.id.ratings);
            item = itemView.findViewById(R.id.item);
            lang = itemView.findViewById(R.id.lang);
            container = itemView.findViewById(R.id.shimmer_view_container);

            container.startShimmerAnimation();
        }
    }

}


