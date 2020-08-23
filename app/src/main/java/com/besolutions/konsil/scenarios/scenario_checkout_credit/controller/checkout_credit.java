package com.besolutions.konsil.scenarios.scenario_checkout_credit.controller;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_checkout_credit.model.secret_code;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.utils.utils;
import com.google.gson.Gson;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.PaymentIntentResult;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardInputWidget;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class checkout_credit extends AppCompatActivity implements NetworkInterface, View.OnClickListener {
    private String paymentIntentClientSecret;
    private Stripe stripe;
    String consultation_price,consultation_id,consultation_type,consultation_promo;
    Button pay;
    secret_code secret_code;
    int num = 0;
    TextView price;
    String payment_failed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_credit);

        //TITLE IS PAYMENT METHOD
        TextView title = findViewById(R.id.title);
        String paymentmethod = getResources().getString(R.string.checkout);
        title.setText(paymentmethod);

        PaymentConfiguration.init(
                "pk_live_naoQPab0j0XZPhG3cmryN3Qk003AB10ONp"
        );

        consultation_price = getIntent().getStringExtra("consultation_price");
        consultation_id = getIntent().getStringExtra("consultation_id");
        consultation_type = getIntent().getStringExtra("consultation_type");
        consultation_promo = getIntent().getStringExtra("consultation_promo");

        //CALL SERVER
        try {
            new Apicalls(this,this).check_out(consultation_price);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //DEFINE VARS
        pay = findViewById(R.id.payButton);
        price = findViewById(R.id.price);

        //CLICK BUTTON
        pay.setOnClickListener(this);

        //SET TEXT
        price.setText("€"+consultation_price);

        //GET STRING
        payment_failed = getResources().getString(R.string.payment_failed);

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        if(num ==0)
        {
            Gson gson = new Gson();
            secret_code = gson.fromJson(""+model.getJsonObject(),secret_code.class);

            try {
                onPaymentSuccess(secret_code.getClientSecret());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            // Payment completed successfully
            String successfull_payment = getResources().getString(R.string.successfull_payment);
            Toasty.success(checkout_credit.this, successfull_payment, Toasty.LENGTH_LONG).show();
            startActivity(new Intent(checkout_credit.this, main_screen.class));

            num =0;
        }



    }

    @Override
    public void OnError(VolleyError error) {
        Toast.makeText(this, ""+error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        //PAY
        if(view.getId() == R.id.payButton)
        {
            CardInputWidget cardInputWidget = checkout_credit.this.findViewById(R.id.cardInputWidget);
            PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
            if (params != null) {
                ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                        .createWithPaymentMethodCreateParams(params, paymentIntentClientSecret);
                stripe.confirmPayment(checkout_credit.this, confirmParams);

                new utils().set_dialog(checkout_credit.this);  //CALL PROGRESS DIALOG

            }
        }
        }


    private void onPaymentSuccess(@NonNull final String secret) throws IOException {

        String stripePublishableKey = "pk_live_naoQPab0j0XZPhG3cmryN3Qk003AB10ONp";
        paymentIntentClientSecret = secret;

        // Configure the SDK with your Stripe publishable key so that it can make requests to the Stripe API
        stripe = new Stripe(
                getApplicationContext(),
                Objects.requireNonNull(stripePublishableKey)
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handle the result of stripe.confirmPayment
        stripe.onPaymentResult(requestCode, data, new PaymentResultCallback(this));
    }

    private final class PaymentResultCallback
            implements ApiResultCallback<PaymentIntentResult> {
        @NonNull private final WeakReference<checkout_credit> activityRef;

        PaymentResultCallback(@NonNull checkout_credit activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onSuccess(@NonNull PaymentIntentResult result) {
            final checkout_credit activity = activityRef.get();
            if (activity == null) {
                return;
            }

            PaymentIntent paymentIntent = result.getIntent();
            PaymentIntent.Status status = paymentIntent.getStatus();
            if (status == PaymentIntent.Status.Succeeded) {

                new utils().dismiss_dialog(checkout_credit.this);  //DISMISS PROGRESS DIALOG

                num = 1;
                try {
                    if(consultation_type.equals("Consultation"))
                    {
                        new Apicalls(checkout_credit.this, checkout_credit.this).confirm_consultation("" + consultation_id, "" + 1,consultation_promo);
                    }
                    else {
                        new Apicalls(checkout_credit.this, checkout_credit.this).confirm_conversation("" + consultation_id, "1",consultation_promo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (status == PaymentIntent.Status.RequiresPaymentMethod) {
                // Payment failed – allow retrying using a different payment method
               Toasty.error(checkout_credit.this,payment_failed,Toasty.LENGTH_LONG).show();
                new utils().dismiss_dialog(checkout_credit.this);  //DISMISS PROGRESS DIALOG
            }
        }

        @Override
        public void onError(@NonNull Exception e) {
            final checkout_credit activity = activityRef.get();
            if (activity == null) {
                return;
            }

            // Payment request failed – allow retrying using the same payment method
            Toasty.error(checkout_credit.this,payment_failed,Toasty.LENGTH_LONG).show();
            new utils().dismiss_dialog(checkout_credit.this);  //DISMISS PROGRESS DIALOG

        }

    }
}
