package com.besolutions.konsil.scenarios.scenario_Consulation_request.Controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.consulation_result;
import com.besolutions.konsil.scenarios.scenario_mian_page.Controller.main_screen;
import com.besolutions.konsil.scenarios.scenario_payment.controller.payment;
import com.besolutions.konsil.utils.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class consulation_request extends AppCompatActivity implements View.OnClickListener {
    ImageView upload_img,upload_file;
    LottieAnimationView upload_img_check,upload_file_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulation_request);
        upload_img =(ImageView)findViewById(R.id.upload_img);
        upload_file=(ImageView)findViewById(R.id.upload_file);

        upload_img_check=(LottieAnimationView)findViewById(R.id.check_img);
        upload_file_check=(LottieAnimationView)findViewById(R.id.check_file);

        upload_img.setOnClickListener(this);
        upload_file.setOnClickListener(this);

        Button complete_req=(Button)findViewById(R.id.complete_req);
        complete_req.setOnClickListener(this);

        set_toolbar_name();


    }

    @Override
    public void onClick(View v) {
        utils utils=new utils();
        if(v.getId()==R.id.upload_img)
    {
        utils.upload_image(this,1);
    }
    else if(v.getId()==R.id.complete_req)
    {
        startActivity(new Intent(consulation_request.this, payment.class));
    }
    else if(v.getId()==R.id.upload_file)
    {
        utils.upload_files(this,2);
    }
    }

    public void set_toolbar_name()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title=(TextView)findViewById(R.id.title);
        String con_req = getResources().getString(R.string.con_req);
        title.setText(con_req);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            if (resultCode == RESULT_OK) {
                {
                    Uri selectedImage = data.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap SelectedPhoto = BitmapFactory.decodeStream(imageStream);
                    Bitmap bitmap = Bitmap.createScaledBitmap(SelectedPhoto, 300, 300, true);

                    if (bitmap != null) {
                        upload_img_check.playAnimation();
                    }
                }
            }
        }
        else if(requestCode==2)
        {
            upload_file_check.playAnimation();
        }
    }
}
