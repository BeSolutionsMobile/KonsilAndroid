package com.besolutions.konsil.scenarios.scenario_Consulation_request.Controller;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.config.Config;
import com.besolutions.konsil.network_check_status.regist_network_broadcast;
import com.besolutions.konsil.scenarios.scenario_Consulation_request.model.consultation_reserve;
import com.besolutions.konsil.scenarios.scenario_compalint_details.compalint_details.Controller.compalint_details;
import com.besolutions.konsil.scenarios.scenario_login.Controller.login;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.scenarios.scenario_payment_methods.controller.pament_method;
import com.besolutions.konsil.utils.firebase_storage;
import com.besolutions.konsil.utils.firebase_storage_pdf;
import com.besolutions.konsil.utils.utils;
import com.google.gson.Gson;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;

import es.dmoral.toasty.Toasty;

public class consulation_request extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    ImageView upload_img, upload_file;
    LottieAnimationView upload_img_check, upload_file_check;
    EditText title, desc;
    consultation_reserve consultation_reserve;
    String consultation_price,doc_name ;
    int paid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulation_request);


        //GET DATA FRPM PREVIOUS PAGE
        consultation_price = getIntent().getStringExtra("consultation_price");
        doc_name = getIntent().getStringExtra("doc_name");


        upload_img = findViewById(R.id.upload_img);
        upload_file = findViewById(R.id.upload_file);

        upload_img_check = findViewById(R.id.check_img);
        upload_file_check = findViewById(R.id.check_file);

        upload_img.setOnClickListener(this);
        upload_file.setOnClickListener(this);

        Button complete_req = findViewById(R.id.complete_req);
        complete_req.setOnClickListener(this);

        title = findViewById(R.id.cons_title);
        desc = findViewById(R.id.cons_desc);

        set_toolbar_name();

       //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(consulation_request.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, PayPalService.class));
    }

    @Override
    public void onClick(View v) {
        utils utils = new utils();
        if (v.getId() == R.id.upload_img) {


            String select_img = getResources().getString(R.string.select_img);


            //SEND ARRAY OF IMAGES
            Intent i = new Intent(Intent.EXTRA_ALLOW_MULTIPLE, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, select_img), 1);


        } else if (v.getId() == R.id.complete_req) {

            String titles = getResources().getString(R.string.short_title);
            String descr = getResources().getString(R.string.desc);

            if (title.getText().length() < 4) {
                Toasty.warning(consulation_request.this, titles, Toasty.LENGTH_SHORT).show();
            } else if (desc.getText().length() < 4) {
                Toasty.warning(consulation_request.this, descr, Toasty.LENGTH_SHORT).show();
            } else {

                int doc_id = getIntent().getIntExtra("doc_id", 0);

                //SEND DATA TO API
                try {
                    new Apicalls(consulation_request.this, consulation_request.this).add_consultation(title.getText().toString(), desc.getText().toString(), "" + doc_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new utils().set_dialog(consulation_request.this);  //CALL PROGRESS DIALOG
//                process_payment();
            }

        } else if (v.getId() == R.id.upload_file) {
            utils.upload_files(this, 2);
        }
    }

    //TOOLBAR NAME
    public void set_toolbar_name() {
        Toolbar mToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title = findViewById(R.id.title);
        String con_req = getResources().getString(R.string.con_req);
        title.setText(con_req);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                {
                    ClipData clipData = data.getClipData();
                    if (clipData == null) {

                        String select_more_imgs = getResources().getString(R.string.select_more_imgs);
                        Toasty.warning(consulation_request.this, select_more_imgs, Toasty.LENGTH_LONG).show();

                    } else if (clipData != null) {
                        //UPLOAD TO FIREBASE
                        firebase_storage firebase_storage = new firebase_storage();
                        firebase_storage.uploadImage(clipData, consulation_request.this, true);
                        upload_img_check.playAnimation();
                    }
                }
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {

                Uri selectedPdf = data.getData();


                //UPLOAD TO FIREBASE
                firebase_storage_pdf firebase_storage = new firebase_storage_pdf();
                firebase_storage.uploadImage(selectedPdf,consulation_request.this,true);
                upload_file_check.playAnimation();
            }
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        if (paid == 0) {

            new utils().dismiss_dialog(consulation_request.this);  //DISMISS PROGRESS DIALOG

            Gson gson = new Gson();
            consultation_reserve = gson.fromJson("" + model.getJsonObject(), consultation_reserve.class);

            Toasty.success(consulation_request.this, consultation_reserve.getMessage(), Toasty.LENGTH_LONG).show();

            //GO TO NEXT PAGE (PAYMENT METHOD)
            Intent intent = new Intent(consulation_request.this, pament_method.class);
            intent.putExtra("doctor_name",doc_name);
            intent.putExtra("consultation_price",consultation_price);
            intent.putExtra("consultation_id",""+consultation_reserve.getId());
            intent.putExtra("consultation_type","Consultation");
            startActivity(intent);


            firebase_storage.images = null;
            firebase_storage_pdf.pdf = null;

        } else if (paid == 1) {
            paid = 0;
        }

    }


    @Override
    public void OnError(VolleyError error) {

    }

}
