package com.besolutions.konsil.scenarios.scenario_mian_page.pattern;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.doctor_list;
import com.besolutions.konsil.scenarios.scenario_mian_page.model.main_screen_list;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class main_screen_adapter extends RecyclerView.Adapter<main_screen_adapter.main_screen_holder> {
    private Context context;
    private ArrayList<main_screen_list> mylist;

    public main_screen_adapter(Context context, ArrayList<main_screen_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public main_screen_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.f_spechial_item, viewGroup, false);
        return new main_screen_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final main_screen_holder viewHolder, final int i) {
        viewHolder.name.setText(mylist.get(i).getName());
        //Glide.with(context).load(mylist.get(i).getImage()).into(viewHolder.img);

        Glide.with(context)
                .load(mylist.get(i).getImage()).placeholder(R.drawable.konzil_logo)
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
                .into(viewHolder.img);

        //SET ON ITEM CLICK
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, doctor_list.class);
                intent.putExtra("id", mylist.get(i).getId());
                intent.putExtra("num", 1);
                context.startActivity(intent);
            }
        });


        //IF ELEMENT ARABIC
        if (new saved_data().get_lan(context).equals("ar")) {
            if (i == 0) {
                viewHolder.item.setBackgroundResource(R.drawable.s_spechial_bg);
            } else if (i == 1) {
                viewHolder.item.setBackgroundResource(R.drawable.f_special_bg);
            } else if (i == mylist.size() - 1) {
                viewHolder.item.setBackgroundResource(R.drawable.f_spechail_bg);
            } else if (i == mylist.size() - 2) {
                viewHolder.item.setBackgroundResource(R.drawable.t_special_bg);
            }
        } else   //IF ANY LANGUAGE ELSE
        {
            if (i == 0) {
                viewHolder.item.setBackgroundResource(R.drawable.f_special_bg);
            } else if (i == 1) {
                viewHolder.item.setBackgroundResource(R.drawable.s_spechial_bg);
            } else if (i == getItemCount() - 1) {
                viewHolder.item.setBackgroundResource(R.drawable.t_special_bg);
            } else if (i == getItemCount() - 2) {
                viewHolder.item.setBackgroundResource(R.drawable.f_spechail_bg);
            }
        }


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class main_screen_holder extends RecyclerView.ViewHolder {
        LinearLayout item;
        TextView name;
        ImageView img;
        ShimmerFrameLayout container;

        main_screen_holder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);

            container = itemView.findViewById(R.id.shimmer_view_container);

            container.startShimmerAnimation();
        }
    }
}
