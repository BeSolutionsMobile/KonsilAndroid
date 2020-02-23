package com.besolutions.konsil.local_data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class saved_data {

    public String get_lan(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("language", Context.MODE_PRIVATE);
        return sharedPreferences.getString("lan", "en");

    }

    public String get_nav_word(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("nav_lan", Context.MODE_PRIVATE);
        return sharedPreferences.getString("nav_lan", "sprache auf deutsch umstellen");
    }

    //GET TOKEN
    public String get_token(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("token", MODE_PRIVATE);
        String token = sharedPreferences.getString("token_key", "0");
        return token;
    }

    //GET NAME
    public String get_name(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "0");
        return name;
    }

    //GET EMAIL
    public String get_email(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "0");
        return email;
    }

    //GET PHONE
    public String get_phone(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String phone = sharedPreferences.getString("phone", "0");
        return phone;
    }

    //GET FINGER_PRINT
    public String get_finger_print(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("finger_print", MODE_PRIVATE);
        String phone = sharedPreferences.getString("finger_print", "no");
        return phone;
    }

    //GET LOGIN STATUS
    public Boolean get_login_status(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login_bool", MODE_PRIVATE);
        Boolean status = sharedPreferences.getBoolean("login_bool", false);
        return status;
    }
}
