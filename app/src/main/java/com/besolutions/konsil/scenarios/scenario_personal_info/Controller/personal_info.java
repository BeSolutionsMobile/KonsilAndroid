package com.besolutions.konsil.scenarios.scenario_personal_info.Controller;

import android.content.Intent;
import android.database.Cursor;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.local_data.send_data;
import com.besolutions.konsil.network_check_status.regist_network_broadcast;
import com.besolutions.konsil.scenarios.scenario_login.Controller.loading;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.scenarios.scenario_personal_info.model.UserInfo;
import com.besolutions.konsil.scenarios.scenario_personal_info.model.root_personal_info;
import com.besolutions.konsil.utils.firebase_storage_one_img;
import com.besolutions.konsil.utils.utils;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import es.dmoral.toasty.Toasty;

public class personal_info extends AppCompatActivity implements NetworkInterface, View.OnClickListener {
    Toolbar mToolbar;
    EditText username, email, password, phone, desc;
    LinearLayout change_photo;
    Button done;
    Bitmap bitmaps;

    root_personal_info root_personal_info;
    UserInfo userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);
        mToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title = findViewById(R.id.title);
        String Personal_Info = getResources().getString(R.string.Personal_info);
        title.setText(Personal_Info);

        //DEFINE ALL ITEMS
        username = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        change_photo = findViewById(R.id.change_photo);
        desc = findViewById(R.id.desc);
        done = findViewById(R.id.done);

        //SET DATA IN ITEMS
        username.setText(new saved_data().get_name(this));
        email.setText(new saved_data().get_email(this));
        phone.setText(new saved_data().get_phone(this));
        desc.setText(new saved_data().get_desc(this));


        done.setOnClickListener(this);
        change_photo.setOnClickListener(this);


        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(personal_info.this);

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        //OPEN DIALOG
        com.besolutions.konsil.scenarios.scenario_login.Controller.loading loading = new loading();
        loading.dialog(personal_info.this, R.layout.successful_edit, .80);


        Gson gson = new Gson();
        root_personal_info = gson.fromJson("" + model.getJsonObject(), root_personal_info.class);

        userInfo = root_personal_info.getUserInfo();

        //SAVE PERSONAL INFORMATION
        new send_data().send_name(this, userInfo.getName());
        new send_data().send_email(this, userInfo.getEmail());
        new send_data().send_phone(this, userInfo.getPhone());
        new send_data().send_descripition(this, desc.getText().toString());

        //CHECK IF THERE IS IMAGE WILL SAVE IT TO SHAREDPREFRENCES OR NOT
        if(!firebase_storage_one_img.imageURL.equals("noImage"))
        {
            send_data.send_image(personal_info.this,firebase_storage_one_img.imageURL); //SAVE IMAGE IN LOCAL DATA
        }



    }

    @Override
    public void OnError(VolleyError error) {
        if (error.networkResponse.statusCode == 402) {

            String email_token = getResources().getString(R.string.email_token);
            Toasty.error(personal_info.this, email_token, Toasty.LENGTH_SHORT).show();

        } else if (error.networkResponse.statusCode == 406) {


            String phone_empty = getResources().getString(R.string.phone_empty);
            Toasty.error(personal_info.this, phone_empty, Toasty.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.done) {

            //VALIDATION IN ALL ELEMENTS
            if (username.getText().toString().length() < 2) {

                String empty_name = getResources().getString(R.string.empty_name);
                Toasty.error(personal_info.this, empty_name, Toasty.LENGTH_SHORT).show();

            } else if (phone.getText().toString().length() < 8) {

                String empty_phone = getResources().getString(R.string.empty_phone);
                Toasty.error(personal_info.this, empty_phone, Toasty.LENGTH_SHORT).show();

            } else if (email.getText().toString().length() < 6) {
                String empty_mail = getResources().getString(R.string.empty_mail);
                Toasty.error(personal_info.this, empty_mail, Toasty.LENGTH_SHORT).show();
            } else {
                try {

                    new Apicalls(personal_info.this, personal_info.this).update_personal_info(username.getText().toString()
                            , phone.getText().toString(), email.getText().toString(), password.getText().toString(), firebase_storage_one_img.imageURL , desc.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (v.getId() == R.id.change_photo) {
            new utils().upload_image(personal_info.this, 1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImage = data.getData();

                Log.e("img_is", selectedImage + "");
                InputStream imageStream = null;



                try {
                    FileOutputStream fos = null;
                    imageStream = getContentResolver().openInputStream(selectedImage);
                    Log.e("img_is", imageStream + "");

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Bitmap SelectedPhoto = BitmapFactory.decodeStream(imageStream);
                bitmaps = Bitmap.createScaledBitmap(SelectedPhoto, 300, 300, true);
                Log.e("img_is", bitmaps + "");

                firebase_storage_one_img firebase_storage = new firebase_storage_one_img();
                firebase_storage.uploadImage(selectedImage, personal_info.this, true);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

}
