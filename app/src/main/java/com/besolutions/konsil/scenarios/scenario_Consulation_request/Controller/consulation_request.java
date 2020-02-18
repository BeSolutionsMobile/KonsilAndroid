package com.besolutions.konsil.scenarios.scenario_Consulation_request.Controller;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
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
import com.besolutions.konsil.utils.firebase_storage;
import com.besolutions.konsil.utils.utils;
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

    private static final int PAYPAL_REQUEST_CODE = 3;

    private static PayPalConfiguration payPalConfiguration = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(Config.CLIENT_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulation_request);

        //START PAYPAL SERVICE
        Intent intent = new Intent(this , PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,payPalConfiguration);
        startService(intent);

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


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this , PayPalService.class));
    }

    @Override
    public void onClick(View v) {
        utils utils = new utils();
        if (v.getId() == R.id.upload_img) {

            //SEND ARRAY OF IMAGES
            Intent i = new Intent( Intent.EXTRA_ALLOW_MULTIPLE, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, "Select Your Photo"),1);


        } else if (v.getId() == R.id.complete_req) {

            int doc_id = getIntent().getIntExtra("doc_id", 0);

            //SEND DATA TO API
            try {
                new Apicalls(consulation_request.this, consulation_request.this).add_consultation("patient", "patient", "" + doc_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            process_payment();

        } else if (v.getId() == R.id.upload_file) {
            utils.upload_files(this, 2);
        }
    }


    //PAYMENT PROCESS
    private void process_payment() {
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal("150"),"USD",
                "Pay now for konsil",PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(consulation_request.this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,payPalConfiguration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);

        startActivityForResult(intent,PAYPAL_REQUEST_CODE);

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
                    ClipData clipData=data.getClipData();
                    if (clipData==null)
                    {
                        Toasty.warning(consulation_request.this,"Please Select More Than Image",Toasty.LENGTH_LONG).show();
                    }
                    else if(clipData != null){
                        //UPLOAD TO FIREBASE
                        firebase_storage firebase_storage = new firebase_storage();
                        firebase_storage.uploadImage(clipData, consulation_request.this, true);
                        upload_img_check.playAnimation();
                    }
                }
            }
        }
        else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
            upload_file_check.playAnimation();
            Uri selected_file = data.getData();

//            firebase_storage firebase_storage = new firebase_storage();
//            firebase_storage.uploadImage(selected_file, consulation_request.this, true);
        }
        }
        else if (requestCode == PAYPAL_REQUEST_CODE)
        {

            if (resultCode == RESULT_OK)
            {
                PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                //VALIDATE ON ALL ITEMS
                if(paymentConfirmation != null)
                {
                    Toasty.success(consulation_request.this, "Successful Payment", Toasty.LENGTH_SHORT).show();
                }

            }

        }
        else if(requestCode == PaymentActivity.RESULT_EXTRAS_INVALID){
            Toasty.error(consulation_request.this, "Invalid", Toasty.LENGTH_SHORT).show();
        }

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        Log.e("paynow",model.getJsonObject()+"");
    }


    @Override
    public void OnError(VolleyError error) {
        Toast.makeText(this, "" + error.networkResponse, Toast.LENGTH_SHORT).show();
    }
}
