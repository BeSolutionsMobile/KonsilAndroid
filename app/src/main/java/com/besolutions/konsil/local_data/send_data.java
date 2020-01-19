package com.besolutions.konsil.local_data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class send_data {

    public static void send_lan(Context context,String lan)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("language",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("lan", lan);
        editor.commit();
    }

    public static void send_word_navigation(Context context,String lan)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("nav_lan",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("nav_lan", lan);
        editor.commit();
    }
}
