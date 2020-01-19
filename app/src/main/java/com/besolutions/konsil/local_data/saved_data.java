package com.besolutions.konsil.local_data;

import android.content.Context;
import android.content.SharedPreferences;

public class saved_data {

   public  String get_lan(Context context)
   {
       SharedPreferences sharedPreferences=context.getSharedPreferences("language",Context.MODE_PRIVATE);
        return sharedPreferences.getString("lan","en");

   }

    public  String get_nav_word(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("nav_lan",Context.MODE_PRIVATE);
        return sharedPreferences.getString("nav_lan","sprache auf deutsch umstellen");

    }
}
