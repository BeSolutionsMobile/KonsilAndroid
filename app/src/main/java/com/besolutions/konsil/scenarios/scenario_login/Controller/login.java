package com.besolutions.konsil.scenarios.scenario_login.Controller;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;

import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.local_data.send_data;

import com.besolutions.konsil.network_check_status.regist_network_broadcast;
import com.besolutions.konsil.scenarios.scenario_login.model.UserInfo;
import com.besolutions.konsil.scenarios.scenario_login.model.login_root;
import com.besolutions.konsil.scenarios.scenario_sign_up.Controller.sign_up;
import com.besolutions.konsil.utils.utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import co.infinum.goldfinger.Goldfinger;
import es.dmoral.toasty.Toasty;

import static com.besolutions.konsil.utils.utils_library.firebase_token;
import static com.besolutions.konsil.utils.utils_library.yoyo;
import static com.besolutions.konsil.utils.utils_library.yoyo_fading;

public class login extends AppCompatActivity implements View.OnClickListener, NetworkInterface {
    Button login;
    CheckBox check;
    TextView signup;
    EditText email, password;
    UserInfo userInfo;
    CheckBox check_finger;
    LoginButton fb_login;
    static CallbackManager callbackManager;
    boolean fb_status = false;
    static String email_fb, id_fab;
    SignInButton sign_in_google;
    GoogleSignInClient mGoogleSignInClient;
    int GOOGLE_SIGN_IN = 0;
    static String personName_G,personEmail_G,personId_G;
    int google_num =0;
    ImageView logoanim;
    Goldfinger goldfinger;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        //MOVE TO THE TOP OF ACTIVITY
        move_to_top();


        login = findViewById(R.id.login);
        check = findViewById(R.id.check);
        signup = findViewById(R.id.signup);
        check_finger = findViewById(R.id.check);
        sign_in_google= findViewById(R.id.sign_in_google);
        logoanim = findViewById(R.id.logoanim);
        goldfinger = new Goldfinger.Builder(login.this).build();



        login.setOnClickListener(this);
        check.setOnClickListener(this);
        signup.setOnClickListener(this);
        sign_in_google.setOnClickListener(this);

        firebase_token();

        //STOP IMAGE LOOPING
        stop_looping();

        //CHECK FINGER PRINT
        setCheck_finger();




        //SET FINGER PRINT
        check_finger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                email = findViewById(R.id.email);
                password = findViewById(R.id.password);

                new send_data().finger_print(login.this, "yes");

            }
        });

        fb_login(); //CALL FACEBOOK LOGIN

        printKeyHash();

        checkLoginStatus();


        //METHOD USING TO GOOGLE SIGN IN
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //SET TEXT IN GOOGLE
        setGooglePlusButtonText(sign_in_google,getResources().getString(R.string.google_login));



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            email = findViewById(R.id.email);
            password = findViewById(R.id.password);

            if (email.getText().toString().length() < 6) {
                String email_txt = getResources().getString(R.string.email_val);
                email.setError(email_txt);  //VALIDATION ON EMAIL
                yoyo(R.id.email, email);
            } else if (password.getText().toString().length() < 8) {
                String password_txt = getResources().getString(R.string.password_val);
                password.setError(password_txt);  //VALIDATION ON PASSWORD
                yoyo(R.id.password, password);
            } else {

                new utils().set_dialog(login.this);  //CALL PROGRESS DIALOG

                //CALL SERVER
                new Apicalls(login.this, login.this).loginUser(email.getText().toString(), password.getText().toString(), firebase_token());
            }
        }
        else if (v.getId() == R.id.signup) {
            startActivity(new Intent(this, sign_up.class));
        }
        else if(v.getId() == R.id.sign_in_google) {
            setSign_in_google();
        }

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(login.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //finish();
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        if ((fb_status == false)&&(google_num == 0)) {
            new utils().dismiss_dialog(login.this);  //DISMISS PROGRESS DIALOG
        }


        Gson gson = new Gson();

        login_root login_root = gson.fromJson(model.getResponse(), login_root.class);

        //GET TOKEN AND SAVE IT IN LOCAL DATA
        send_data.save_token(login.this, login_root.getToken());

        //SAVE PERSONAL INFO
        userInfo = login_root.getUserInfo();

        new send_data().send_name(this, userInfo.getName());
        new send_data().send_email(this, userInfo.getEmail());
        new send_data().send_phone(this, userInfo.getPhone());
        send_data.send_image(login.this, userInfo.getImageUrl()); //SAVE IMAGE IN LOCAL DATA

//        Log.e("img_url",userInfo.getImageUrl());

        new send_data().login_status(login.this, true);  //SET TRUE TO MAKE LOGIN AFTER FIRST LOGIN


        loading loading = new loading();
        loading.dialog(login.this, R.layout.successful_login, .80);

        fb_status = false;


    }

    @Override
    public void OnError(VolleyError error) {
        if (error.networkResponse.statusCode == 401) {
            new utils().dismiss_dialog(login.this);
            String error_mail_pass = getResources().getString(R.string.error_mail_pass); //ERROR IN MAIL OR PASSWORD
            Toasty.error(login.this, error_mail_pass, Toasty.LENGTH_SHORT).show();
        } else if (error.networkResponse.statusCode == 402) {
            //CALL SERVER IN LOGIN

            if(google_num == 1) //IF THIS IS GOOGLE
            {
                new Apicalls(login.this, login.this).loginUser(personEmail_G, personId_G, firebase_token());
            }
            else { //IF THIS IS FACEBOOK
                new Apicalls(login.this, login.this).loginUser(email_fb, id_fab, firebase_token());
            }

        } else if (error.networkResponse.statusCode == 405) {
            new utils().dismiss_dialog(login.this);
            Toasty.error(login.this, "Email is Empty", Toasty.LENGTH_SHORT).show();
        } else if (error.networkResponse.statusCode == 406) {
            new utils().dismiss_dialog(login.this);
            Toasty.error(login.this, "Password is Empty", Toasty.LENGTH_SHORT).show();
        } else if (error.networkResponse.statusCode == 407) {
            new utils().dismiss_dialog(login.this);
            Toasty.error(login.this, "Mobile Token is Empty", Toasty.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    /**
     * ALL OF THIS PAGE RELATED WITH FACEBOOK
     */

    //FACEBOOK LOGIN
    private void fb_login() {
        callbackManager = CallbackManager.Factory.create();
        fb_login = findViewById(R.id.fb_login);

        //SET PERMISSION TO GET DATA
        fb_login.setReadPermissions("public_profile", "user_friends", "user_photos", "email", "user_birthday", "public_profile", "contact_email");

        fb_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            AccessToken.setCurrentAccessToken(null);
            Profile.setCurrentProfile(null);

            if (currentAccessToken == null) {
            } else
                loadUserProfile(currentAccessToken);

        }
    };


    //GET DATA FROM FACEBOOK
    private void loadUserProfile(AccessToken newAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    id_fab = object.getString("id");
                    email_fb = object.getString("email");
                    String user_img ="https://graph.facebook.com/"+object.getString("id")+"/picture";

                    fb_status = true; //SET STATUS TRUE


                    //CALL REGIST API
                    new Apicalls(login.this, login.this).insertUser(first_name + "" + last_name, "01152314753", email_fb, id_fab, "2", user_img, new saved_data().get_lan(login.this), firebase_token());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void checkLoginStatus() {
        if (AccessToken.getCurrentAccessToken() != null) {
            loadUserProfile(AccessToken.getCurrentAccessToken());
        }
    }

    //GET HASH KEY FACEBOOK

    public void printKeyHash() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.besolutions.konsil",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    /**
     * GOOGLE SIGN IN
     */

    //GOOGLE SIGN IN
    private void setSign_in_google() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    // RESULT CAME FROM GOOGLE
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(login.this);
            if (acct != null) {
                 personName_G = acct.getDisplayName();
                 personEmail_G = acct.getEmail();
                 personId_G = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                //SEND IMAGE TO SERVER
                String image = ""+personPhoto;
                if(personPhoto == null)
                {
                    image = "";
                }
                else {
                    send_data.send_image(login.this, image); //SAVE IMAGE IN LOCAL DATA
                }


                 google_num = 1; //THIS IS USED TO KNOW IF FACEBOOK OR GMAIL


                //CALL REGIST API
               new Apicalls(login.this, login.this).insertUser(personName_G, "01152314753", personEmail_G, personId_G, "2", image, new saved_data().get_lan(login.this), firebase_token());

            }

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("result", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    protected void setGooglePlusButtonText(SignInButton signInButton, String buttonText) {
        // Find the TextView that is inside of the SignInButton and set its text
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setText(buttonText);
                return;
            }
        }
    }

    //ANIMATION IMAGE FOR SPLASH SCREEN
    void move_to_top()
    {




                //SET ITEM VISABLE AFTER 2 SECONDS
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        logoanim.animate().y(50f);

                        //SET ITEM VISABLE AFTER 2 SECONDS
                        Handler handler1 = new Handler();
                        handler1.postDelayed(new Runnable() {
                            public void run() {

                                //SET ALL COMPONET VISABLE
                                LinearLayout visability = findViewById(R.id.visible);



                                yoyo_fading(R.id.visible, visability);

                                visability.setVisibility(View.VISIBLE);



                            }
                        }, 500);

                    }
                }, 3500);



    }

    //USING TO STOP ANIMATION
    void stop_looping()
    {

        Glide.with(this).asGif().load(R.raw.anim).listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                resource.setLoopCount(1);
                resource.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        //do whatever after specified number of loops complete
                    }
                });
                return false;
            }}).into(logoanim);
    }

    //CHECK GOLDFINGER OR NOT
    void setCheck_finger()
    {
        if(goldfinger.hasFingerprintHardware() == false)
        {
            check.setVisibility(View.GONE);

        }
        else if(goldfinger.hasEnrolledFingerprint() == false)
        {
            check.setVisibility(View.GONE);
        }
    }
}