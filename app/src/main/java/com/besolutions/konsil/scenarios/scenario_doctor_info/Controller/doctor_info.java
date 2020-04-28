package com.besolutions.konsil.scenarios.scenario_doctor_info.Controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.network_check_status.regist_network_broadcast;
import com.besolutions.konsil.scenarios.scenario_compalint_details.compalint_details.Controller.compalint_details;
import com.besolutions.konsil.scenarios.scenario_doctor_info.model.Doctor;
import com.besolutions.konsil.scenarios.scenario_doctor_info.model.root_details;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.Controller.request_online_conversation;
import com.besolutions.konsil.scenarios.scenario_Consulation_request.Controller.consulation_request;
import com.besolutions.konsil.scenarios.scenario_sign_up.Controller.sign_up;
import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;


public class doctor_info extends AppCompatActivity implements View.OnClickListener, NetworkInterface {
    RatingBar ratings;
    Button request_consulation, online_res;
    Doctor doctors;
    int doc_id;
    String doc_id_st;
    String No_bioavailable;
    ProgressBar pg;
    TextView degree;


    ShimmerFrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_info);

        //DEFINE ALL VARS
        ratings = findViewById(R.id.ratings);
        request_consulation = findViewById(R.id.request_consulation);
        online_res = findViewById(R.id.online_res);
        pg = findViewById(R.id.pg);

        container = findViewById(R.id.shimmer_view_container);

        container.startShimmerAnimation();

        ratings.setRating(4);

        set_toolbar_name();
        doc_id_st = getIntent().getStringExtra("id");
        new Apicalls(this, this).doctor_details(doc_id_st);

        //SET RESOURCE FILE FOR CHECK IS NULL OR NOT
        No_bioavailable = getResources().getString(R.string.nobioavailable);

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(doctor_info.this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.request_consulation) {
            Intent intent = new Intent(doctor_info.this, consulation_request.class);
            intent.putExtra("doc_id", doc_id);
            intent.putExtra("consultation_price", ""+doctors.getConsultationPrice());
            intent.putExtra("doc_name", ""+doctors.getName());

            startActivity(intent);
        } else if (v.getId() == R.id.online_res) {

            if (doctors.getImageUrl() == null) {
                Toasty.warning(doctor_info.this, "Please wait until loading all data ...", Toasty.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(doctor_info.this, request_online_conversation.class);
                intent.putExtra("image", doctors.getImageUrl());
                intent.putExtra("doc_name", doctors.getName());
                intent.putExtra("doc_title", doctors.getJobTitle() + "");
                intent.putExtra("conversation_price", doctors.getConversationPrice() + "");
                intent.putExtra("rating", doctors.getRate());
                intent.putExtra("doc_id", doc_id_st);
                startActivity(intent);
            }
        }
    }

    public void set_toolbar_name() {
        Toolbar mToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title = findViewById(R.id.title);
        String doc_info = getResources().getString(R.string.doc_info);

        title.setText(doc_info);
    }

    @Override
    public void OnStart() {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void OnResponse(ResponseModel model) {
        //PROGRESS BAR VISABILITY
        pg.setVisibility(View.GONE);

        de.hdodenhof.circleimageview.CircleImageView doc_img = findViewById(R.id.doc_img);
        TextView doc_name = findViewById(R.id.doc_name);
        TextView doc_job = findViewById(R.id.doc_job);
        ReadMoreTextView doc_desc = findViewById(R.id.doc_desc);
        TextView price = findViewById(R.id.price);
        TextView consultation = findViewById(R.id.consultation);
        TextView conversation = findViewById(R.id.conversation);
        RatingBar ratingBar = findViewById(R.id.ratings);
        degree = findViewById(R.id.degree);


        Gson gson = new Gson();
        root_details root_details = gson.fromJson("" + model.getJsonObject(), root_details.class);
        doctors = root_details.getDoctor();

        //SET DATA
        //Picasso.with(doctor_info.this).load(doctors.getImageUrl()).into(doc_img);

        Glide.with(doctor_info.this)
                .load(doctors.getImageUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        container.stopShimmerAnimation();
                        return false;
                    }
                })
                .into(doc_img);

        doc_name.setText(doctors.getName());
        price.setText("" + doctors.getConsultationPrice());
        consultation.setText("" + doctors.getTotalConsultation());
        conversation.setText("" + doctors.getTotalConversation());
        degree.setText(""+doctors.getDegree());
        ratingBar.setRating(doctors.getRate());

        //SET CHECK ON DOC JOB
        if(doctors.getJobTitle() == null)
        {
            doc_job.setVisibility(View.GONE); //IF NULL SET VISIBALITY GONe
        }
        else {
            doc_job.setText(""+doctors.getJobTitle());


        }

        //SET CHECK ON DOCTOR_DESCRIPITION IF NULL OR NOT
        if (doctors.getBio() == null) {
            doc_desc.setText(No_bioavailable);  //IF ITEMS RETURN WITH NULL
        } else {
            doc_desc.setText("" + doctors.getBio());  //IF ITEMS RETURN WITH DATA

        }

        doc_id = doctors.getId();

        //SET ON CLICK DATA
        online_res.setOnClickListener(this);
        request_consulation.setOnClickListener(this);


    }

    @Override
    public void OnError(VolleyError error) {
       //PROGRESS BAR VISABILITY GONE
        pg.setVisibility(View.GONE);
    }
}
