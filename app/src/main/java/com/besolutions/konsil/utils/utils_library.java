package com.besolutions.konsil.utils;

import android.util.Log;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class utils_library {

    ///RETURN TOKEN KEY FIREBASE
   static String token;

    public static String firebase_token() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = instanceIdResult.getToken();
                Log.e("token_is",token);
                // send it to server
            }
        });
        return token;
    }

    ///USING YOYO LIBRARY
   public static void yoyo(int id, View v) {
        YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(1)
                .playOn(v.findViewById(id));
    }
}
