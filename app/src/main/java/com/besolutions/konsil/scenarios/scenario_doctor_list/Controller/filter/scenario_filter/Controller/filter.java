package com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.scenario_filter.Controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.doctor_list;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.scenario_filter.model.filter_reserv_list;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.scenario_filter.pattern.filter_item_adapter;
import com.besolutions.konsil.scenarios.scenario_doctor_list.model.Degree;
import com.besolutions.konsil.utils.utils_adapter;


import java.util.ArrayList;
import java.util.Objects;

public class filter implements View.OnClickListener {

    private Context context;
    private static Dialog dialog;
    private int stars_num;
    private Degree[] Degree;

    public void dialog(final Context context, int resource, double widthh, Degree[] Degree) {

        this.Degree = Degree;
        this.context = context;

        dialog = new Dialog(context);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        //RATE
        rating();

        //FILTER LIST BUTTONS DATA IN RECYCLERVIEW
        set_data_in_view();

        Button submit = dialog.findViewById(R.id.submit);
        submit.setOnClickListener(this);

        dialog.getWindow().setLayout(width, height);


        dialog.show();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit) {

            if (rating() >= 1) {
                Intent intent = new Intent(context, doctor_list.class);
                intent.putExtra("num", 0);
                intent.putExtra("stars_num", rating());
                context.startActivity(intent);
                ((AppCompatActivity) context).finish();

            } else {
                Toast.makeText(context, "You must select rate", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //SET ON RATING
    private int rating() {
        RatingBar ratingBar = dialog.findViewById(R.id.ratings);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                stars_num = Math.round(rating);
            }
        });
        return stars_num;
    }

    //SET DATA IN RECYCLER VIEW
    private void set_data_in_view() {
        RecyclerView radio_list = dialog.findViewById(R.id.radio_list);
        ArrayList<filter_reserv_list> arrayList = new ArrayList<>();

        for (int index = 0; index < Degree.length; index++) {
            arrayList.add(new filter_reserv_list(Degree[index].getId(), Degree[index].getDegree()));
        }

        utils_adapter utils_adapter = new utils_adapter();
        utils_adapter.griddAdapters(radio_list, new filter_item_adapter(context, arrayList), context, 2);

    }
}