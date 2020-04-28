package com.besolutions.konsil.scenarios.scenario_request_online_conversation.Controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.config.Config;
import com.besolutions.konsil.network_check_status.regist_network_broadcast;
import com.besolutions.konsil.scenarios.scenario_Consulation_request.Controller.consulation_request;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.scenarios.scenario_payment.controller.payment;
import com.besolutions.konsil.scenarios.scenario_payment_methods.controller.pament_method;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.model.Datum;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.model.conversation_reserve;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.model.root_appointment;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.pattern.conversation_item_adapter;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.model.conversation_reserv_list;
import com.besolutions.konsil.utils.utils_adapter;
import com.google.gson.Gson;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class request_online_conversation extends AppCompatActivity implements View.OnClickListener, NetworkInterface {
    RecyclerView reserv_list;
    Button complete_req;
    Datum[] datnum;
    conversation_reserve conversation_reserve;
    RatingBar ratingBar;
    de.hdodenhof.circleimageview.CircleImageView ci_image;
    TextView doc_name, job_title, price;
    int complete_req_status = 0; //THIS FLAG USE  TO KNOW WHAT RESPONSE WILL COME FROM SERVER

    int PAYPAL_REQUEST_CODE = 3;
    ArrayList<conversation_reserv_list> arrayList = new ArrayList<>(); //DEFINE APPOIENMENT LIST

    String doc_id;

    TextView nomsg;
    ProgressBar pg;

    TextView choose_date;
    String conversation_price,doctor_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_online_conversation);
        complete_req = findViewById(R.id.complete_req);
        complete_req.setOnClickListener(this);

        doc_id = getIntent().getStringExtra("doc_id");
        doctor_name = getIntent().getStringExtra("doc_name");


        set_toolbar_name();

        //SET DATA
        set_doc_details();

        //DATA FROM SERVER
        // get_appoienments("2020-02-13");

        //DEFINE ALL VARS
        nomsg = findViewById(R.id.nomsg);
        pg = findViewById(R.id.pg);
        choose_date = findViewById(R.id.choose_date);

        //SET ON CLICK
        choose_date.setOnClickListener(this);

        //SET TEXT VIEW NO DATA FOUND
        String nodates = getResources().getString(R.string.nodates);
        nomsg.setText(nodates);

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(request_online_conversation.this);


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.complete_req) {
            if ((conversation_item_adapter.radio_id.equals("1000"))||(nomsg.getText().toString().equals(getResources().getString(R.string.nodates)))) {

                String choose_time = getResources().getString(R.string.choose_time);
                Toasty.error(request_online_conversation.this, choose_time, Toasty.LENGTH_LONG).show();
            } else {
                try {
                    new Apicalls(request_online_conversation.this, request_online_conversation.this).reserve_conversation(doc_id, conversation_item_adapter.radio_id);
                    complete_req_status = 1;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (v.getId() == R.id.choose_date) {
            get_date_picker();

        }
    }

    public void set_toolbar_name() {
        Toolbar mToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title = findViewById(R.id.title);
        String r_o_c = getResources().getString(R.string.request_online_converstaion);
        title.setText(r_o_c);
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        pg.setVisibility(View.GONE);   //SET VISIBALITY GONE GOR BAR

        //CHECK TO KNOW WHAT REQUEST WILL COME

        //FIRST ITEM IS GET DATA FROM SERVER
        if (complete_req_status == 0) {
            Gson gson = new Gson();
            root_appointment root_appointment = gson.fromJson("" + model.getJsonObject(), root_appointment.class);
            datnum = root_appointment.getData();

            reserv_list = findViewById(R.id.reserv_list);


            if (arrayList.size() > 0) {
                arrayList.clear(); //CLEAR LIST WHEN LOADING APPOINMENTS EVERY TIME
            }

            //CHECK IF THERE IS DATA OR NOT
            if (datnum.length == 0) {
                String nodates = getResources().getString(R.string.nodates);
                nomsg.setText(nodates);   //IF THERE IS DATA RETURN NO DATA FOUND

            } else {

                nomsg.setText("");

                //LOOP TO GET DATA
                for (int index = 0; index < datnum.length; index++) {
                    arrayList.add(new conversation_reserv_list("" + datnum[index].getId(), datnum[index].getFrom(), datnum[index].getTo()));
                }

            }

            utils_adapter utils_adapter = new utils_adapter();
            utils_adapter.Adapter(reserv_list, new conversation_item_adapter(this, arrayList), this);

        }

        //SECOND THING GO TO PAYMENT
        else if (complete_req_status == 1) {
            Gson gson = new Gson();
            conversation_reserve = gson.fromJson("" + model.getJsonObject(), conversation_reserve.class);
            Toasty.success(request_online_conversation.this, conversation_reserve.getMessage(), Toasty.LENGTH_SHORT).show();

            //GO TO NEXT PAGE (PAYMENT METHOD)
            Intent intent = new Intent(request_online_conversation.this, pament_method.class);
            intent.putExtra("doctor_name",doctor_name);
            intent.putExtra("consultation_price",conversation_price);
            intent.putExtra("consultation_id",""+conversation_reserve.getId());
            intent.putExtra("consultation_type","Conversation");
            startActivity(intent);

            complete_req_status = 0;
        } else if (complete_req_status == 2) {
            complete_req_status = 1;
            startActivity(new Intent(request_online_conversation.this, main_screen.class));
        }
    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);
    }

    //SET DOCTOR_DETAILS
    void set_doc_details() {
        // GET DATA FROM PREVIOUS PAGE
        String image = getIntent().getStringExtra("image");
        String docName = getIntent().getStringExtra("doc_name");
        String jobTitle = getIntent().getStringExtra("doc_title");
        conversation_price = getIntent().getStringExtra("conversation_price");
        int rating_bar = getIntent().getIntExtra("rating", 0);

        ci_image = findViewById(R.id.doc_img);
        doc_name = findViewById(R.id.doc_name);
        job_title = findViewById(R.id.job_title);
        ratingBar = findViewById(R.id.ratings);

        price = findViewById(R.id.price);

        //SET DATA
        Picasso.with(this).load(image).into(ci_image);
        doc_name.setText(docName);
        job_title.setText(jobTitle);
        ratingBar.setRating(rating_bar);
        price.setText(conversation_price);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PAYPAL_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                //VALIDATE ON ALL ITEMS
                if (paymentConfirmation != null) {
                    String Successful_Payment = getResources().getString(R.string.successfull_payment);
                    Toasty.success(request_online_conversation.this, Successful_Payment, Toasty.LENGTH_LONG).show();
                    try {
                        new Apicalls(request_online_conversation.this, request_online_conversation.this).confirm_conversation("" + conversation_reserve.getId(), "1");
                        complete_req_status = 2;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }
    }


    //GET DATE PICKER
    void get_date_picker() {
        // TODO Auto-generated method stub
        //To show current date in the datepicker
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker;

        mDatePicker = new DatePickerDialog(request_online_conversation.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                // TODO Auto-generated method stub
                /*      Your code   to get date and time    */
                selectedmonth = selectedmonth + 1;
                //GET TIME PICKER
                choose_date.setText(selectedyear + "-" + selectedmonth + "-" + selectedday);
                //GET DATA FROM SERVER
                get_appoienments(choose_date.getText().toString());
                pg.setVisibility(View.VISIBLE); //SET PROGRESS DIALOG VISIBLE

            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select Date");
        mDatePicker.show();
    }

    //CALL SERVER FOR APPOIENMENTS
    void get_appoienments(String Date) {
        //NO DATES
        try {
            new Apicalls(this, this).appointment(doc_id, Date);
            conversation_item_adapter.radio_id = "1000";
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
