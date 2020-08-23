package com.besolutions.konsil.scenarios.scenario_be_a_doctor.Controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.besolutions.konsil.R;
import com.besolutions.konsil.utils.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class be_a_doctor extends AppCompatActivity implements View.OnClickListener {
     LottieAnimationView check_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.be_a_doctor);
        set_toolbar_name();

        CardView uplaod_img=(CardView)findViewById(R.id.upload_img);
        check_img=(LottieAnimationView)findViewById(R.id.check_img);
        uplaod_img.setOnClickListener(this);
    }

    public void set_toolbar_name()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title=(TextView)findViewById(R.id.title);
        String be_a_doc=getResources().getString(R.string.be_a_doctor);
        title.setText(be_a_doc);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.upload_img)
        {
            utils utils=new utils();
            utils.upload_image(this,1);
        }
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
                        check_img.playAnimation();
                    }
                }
            }
        }
    }
}
