package com.besolutions.konsil.scenarios.scenario_personal_info.Controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.local_data.send_data;
import com.besolutions.konsil.scenarios.scenario_login.Controller.loading;
import com.besolutions.konsil.scenarios.scenario_login.Controller.login;
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

        done.setOnClickListener(this);
        change_photo.setOnClickListener(this);


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

        //SAVE PERSONLA INFORMATION
        new send_data().send_name(this, userInfo.getName());
        new send_data().send_email(this, userInfo.getEmail());
        new send_data().send_phone(this, userInfo.getPhone());

        //OPEN DIALOG SUCCESS EDIT


    }

    @Override
    public void OnError(VolleyError error) {
        if (error.networkResponse.statusCode == 402) {
            Toasty.error(personal_info.this, " Email Has Been Taken", Toasty.LENGTH_SHORT).show();
        } else if (error.networkResponse.statusCode == 406) {
            Toasty.error(personal_info.this, "phone is Empty or less than 8 numbers", Toasty.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.done) {

            //VALIDATION IN ALL ELEMENTS
            if (username.getText().toString().length() < 2) {
                Toasty.error(personal_info.this, " Name is Empty or less than 2 chars", Toasty.LENGTH_SHORT).show();
            } else if (phone.getText().toString().length() < 8) {
                Toasty.error(personal_info.this, "Phone is Empty or less than 8 numbers", Toasty.LENGTH_SHORT).show();
            } else if (email.getText().toString().length() < 6) {
                Toasty.error(personal_info.this, "Email is Empty or short", Toasty.LENGTH_SHORT).show();
            } else {
                try {

                    new Apicalls(personal_info.this, personal_info.this).update_personal_info(username.getText().toString()
                            , phone.getText().toString(), email.getText().toString(), password.getText().toString(), firebase_storage_one_img.imageURL);
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
                InputStream imageStream = null;
                try {
                    FileOutputStream fos = null;
                    imageStream = getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Bitmap SelectedPhoto = BitmapFactory.decodeStream(imageStream);
                bitmaps = Bitmap.createScaledBitmap(SelectedPhoto, 300, 300, true);

                firebase_storage_one_img firebase_storage = new firebase_storage_one_img();
                firebase_storage.uploadImage(selectedImage, personal_info.this, true);
            }
        }
    }
}
