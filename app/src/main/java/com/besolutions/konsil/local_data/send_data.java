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

    //SAVE TOKEN
    public static void save_token(Context context,String token)
    {
            //SAVE LANGUAGE STATUS
            SharedPreferences sharedPreferences=context.getSharedPreferences("token",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("token_key", token);
            editor.commit();
    }

    //SAVE PERSONAL_NAME
    public static void send_name(Context context,String name)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("personal_info",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name", name);
        editor.commit();
    }

    //SAVE PERSONAL_NAME
    public static void send_email(Context context,String email)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("personal_info",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("email", email);
        editor.commit();
    }

    //SAVE PERSONAL_PHONE
    public static void send_phone(Context context,String email)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("personal_info",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("phone", email);
        editor.commit();
    }

    //SAVE PERSONAL_DESCRIPITION
    public static void send_descripition(Context context,String desc)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("personal_info",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("desc", desc);
        editor.commit();
    }

    //SAVE PERSONAL_Image
    public static void send_image(Context context,String img)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("personal_info",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("image", img);
        editor.commit();
    }

    //SAVE FINGER PRINT
    public static void finger_print(Context context,String yes)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("finger_print",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("finger_print", yes);
        editor.commit();
    }

    //SET LOGIN TRUE
    public static void login_status(Context context,boolean status)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("login_bool",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("login_bool", status);
        editor.commit();
    }
}
