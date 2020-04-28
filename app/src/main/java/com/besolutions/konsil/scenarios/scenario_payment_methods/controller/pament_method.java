package com.besolutions.konsil.scenarios.scenario_payment_methods.controller;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.config.Config;
import com.besolutions.konsil.scenarios.scenario_Consulation_request.Controller.consulation_request;
import com.besolutions.konsil.scenarios.scenario_Consulation_request.model.consultation_reserve;
import com.besolutions.konsil.scenarios.scenario_checkout_credit.controller.checkout_credit;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

import es.dmoral.toasty.Toasty;

public class pament_method extends AppCompatActivity implements NetworkInterface, View.OnClickListener {
    String consultation_price,consultation_type,doc_name,consultation_id;
    TextView price,consultation,doctor;
    ImageView paypal,credit;

    //PAYPAL CONFIGRATIONS
    private static final int PAYPAL_REQUEST_CODE = 3;
    private static PayPalConfiguration payPalConfiguration = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION).clientId(Config.CLIENT_ID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pament_method);

        //TITLE IS PAYMENT METHOD
        TextView title = findViewById(R.id.title);
        String paymentmethod = getResources().getString(R.string.paymentmethod);
        title.setText(paymentmethod);

        //START PAYPAL SERVICE
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration);
        startService(intent);

        //GET DATA FRPM PREVIOUS PAGE
        consultation_price = getIntent().getStringExtra("consultation_price");
        doc_name = getIntent().getStringExtra("doctor_name");
        consultation_type = getIntent().getStringExtra("consultation_type");
        consultation_id = getIntent().getStringExtra("consultation_id");

        //DEFINE VAR
        price = findViewById(R.id.price);
        doctor = findViewById(R.id.doc_name);
        consultation = findViewById(R.id.type);
        paypal = findViewById(R.id.paypal);
        credit = findViewById(R.id.credit);

        //SET DATA IN TEXTS
        price.setText("€"+consultation_price);
        doctor.setText(doc_name);
        consultation.setText(consultation_type);

        //SET BUTTONS CLICK
        paypal.setOnClickListener(this);
        credit.setOnClickListener(this);


    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

    }

    @Override
    public void OnError(VolleyError error) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.paypal)
        {
            process_payment();
        }
        else if(view.getId() == R.id.credit)
        {
            Intent intent = new Intent(pament_method.this, checkout_credit.class);
            intent.putExtra("consultation_price",consultation_price);
            intent.putExtra("consultation_id",consultation_id);
            intent.putExtra("consultation_type",consultation_type);
            startActivity(intent);
        }
    }

    //PAYMENT PROCESS
    private void process_payment() {

        String pay_konsil = getResources().getString(R.string.pay_konsil);
        String consultation_price = getIntent().getStringExtra("consultation_price");

        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(consultation_price), "EUR",
                pay_konsil, PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(pament_method.this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);

        startActivityForResult(intent, PAYPAL_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                //VALIDATE ON ALL ITEMS
                if (paymentConfirmation != null) {

                    String successfull_payment = getResources().getString(R.string.successfull_payment);
                    Toasty.success(pament_method.this, successfull_payment, Toasty.LENGTH_LONG).show();
                    startActivity(new Intent(pament_method.this, main_screen.class));

                    try {
                         new Apicalls(pament_method.this, pament_method.this).confirm_consultation("" + consultation_id, "" + 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else if (requestCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            String Invalid = getResources().getString(R.string.Invalid);
            Toasty.error(pament_method.this, Invalid, Toasty.LENGTH_SHORT).show();
        }
    }
}
