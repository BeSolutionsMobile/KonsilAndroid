package com.besolutions.konsil.local_data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

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

    //GET TOKEN
    public  String get_token(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("token",MODE_PRIVATE);
        String token=sharedPreferences.getString("token_key","0");
        return token;
    }
}
