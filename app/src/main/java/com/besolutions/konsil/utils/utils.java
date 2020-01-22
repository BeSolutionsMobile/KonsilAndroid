package com.besolutions.konsil.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import java.util.Locale;


public class utils {

    Context context;

   /* public utils(Context context) {
        this.context = context;
    }*/

    /**
     * SPLASH SCREEN
     */
      public void splash_screen(final Context context, final Class second_class)
       {
           new Thread(new Runnable() {
               public void run() {
                   try {
                       // sleep during 800ms
                       Thread.sleep(3000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   // start HomeActivity
                   Intent intent=new Intent(context, second_class);
                   context.startActivity(intent);
                   ((AppCompatActivity)context).finish();
               }
           }).start();
       }

    /**
     * Upload Image
     */
    public void upload_image(Context context,int requestCode)
    {
        Intent i = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((AppCompatActivity)context).startActivityForResult(Intent.createChooser(i, "Select Your Photo"),requestCode);
    }

    /**
     * Upload Files
     */
    public void upload_files(Context context,int requestCode)
    {
        Intent i = new Intent( Intent.ACTION_OPEN_DOCUMENT);
        i.setType("application/pdf");
        i.addCategory(Intent.CATEGORY_OPENABLE);
        ((AppCompatActivity)context).startActivityForResult(i,requestCode);

    }

    /**
     * REPLACE FRAGMENT
     */
    public void Replace_Fragment(Fragment fragment, int id, Context context)
    {
        //ADD FRAGMENT TO ACTIVITY
        Fragment home=fragment;
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction()
          .replace(id, home ).commit();
    }

    /**
     * convert to byte array
     */
    public Bitmap convertToBitmap(byte[] b){
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * SET LANGUAGE
     */
   public void set_language(String Lan,Context context)
    {
        Resources resources=context.getResources();
        DisplayMetrics displayMetrics=resources.getDisplayMetrics();
        Configuration configuration=resources.getConfiguration();
        configuration.setLocale(new Locale(Lan.toLowerCase()));
        resources.updateConfiguration(configuration,displayMetrics);
    }

}
