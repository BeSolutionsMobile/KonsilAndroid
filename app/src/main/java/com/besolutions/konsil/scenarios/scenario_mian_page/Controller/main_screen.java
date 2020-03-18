package com.besolutions.konsil.scenarios.scenario_mian_page.Controller;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.konsil.NavigationDrawerCallbacks;
import com.besolutions.konsil.NavigationDrawerFragment;
import com.besolutions.konsil.NetworkLayer.Apicalls;
import com.besolutions.konsil.NetworkLayer.NetworkInterface;
import com.besolutions.konsil.NetworkLayer.ResponseModel;
import com.besolutions.konsil.R;

import com.besolutions.konsil.scenarios.scenario_mian_page.model.all_specialitiesDatum;
import com.besolutions.konsil.scenarios.scenario_mian_page.model.all_specialitiesroot_specialities;
import com.besolutions.konsil.scenarios.scenario_mian_page.model.main_screen_list;
import com.besolutions.konsil.scenarios.scenario_mian_page.pattern.main_screen_adapter;
import com.besolutions.konsil.scenarios.scenario_my_consultations.Controlller.my_consultations;
import com.besolutions.konsil.scenarios.scenario_personal_info.Controller.personal_info;
import com.besolutions.konsil.scenarios.scenario_policy.controller.policy;
import com.besolutions.konsil.scenarios.scenario_terms_of_use.Controller.terms_of_use;
import com.besolutions.konsil.scenarios.scenarios_faq.controller.faq;
import com.besolutions.konsil.network_check_status.regist_network_broadcast;
import com.besolutions.konsil.utils.utils;
import com.besolutions.konsil.utils.utils_adapter;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;

public class main_screen extends AppCompatActivity implements NavigationDrawerCallbacks, View.OnClickListener, NetworkInterface {
    Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    int num = 0;
    TextView title;
    utils utils;
    all_specialitiesDatum[] all_specialitiesDatumsss;
    ProgressBar pg;
    static TextView tv_check_connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        tv_check_connection = findViewById(R.id.tv_check_connection);


        utils = new utils();

        mToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        title = findViewById(R.id.title);
        String Spechalist = getResources().getString(R.string.Specialist);
        title.setText(Spechalist);


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        //CHANGE LANGUAGE
        LinearLayout change_lan = findViewById(R.id.change_lan);
        change_lan.setOnClickListener(this);


        //PROGRESS DIALOG
        pg = findViewById(R.id.pg);



        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(main_screen.this);


    }




    @Override
    public void onNavigationDrawerItemSelected(int position) {

        // update the main content by replacing fragments
        if (position == 0) {
            if (num == 0) {
                num = 1;
            } else {
                startActivity(new Intent(this, personal_info.class)); //GO TO MY PERSONAL INFO
            }
        } else if (position == 1) {
            startActivity(new Intent(this, my_consultations.class));  //GO TO MY CONSULTATION
        } else if (position == 2) {
            startActivity(new Intent(this, faq.class)); //GO TO FAQ
        } else if (position == 3) {
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.konsilmed.com/privacy"));
            startActivity(browse);       //GO TO PRIVACY
        } else if (position == 4) {
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.konsilmed.com/treatment-in-germany"));
            startActivity(browse);       //GO TO TREATMENT IN GERMANY
        } else if (position == 5) {
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.konsilmed.com/terms"));
            startActivity(browse);       //GO TO TERMS OF USE
        }
    }


    @Override
    public void onBackPressed() {

        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
        } else {

            moveTaskToBack(true);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.change_lan) {
            language_filter language_filter = new language_filter();
            language_filter.dialog_language(main_screen.this, R.layout.language_filter, .70);
            mNavigationDrawerFragment.closeDrawer();
            mNavigationDrawerFragment.closeDrawer();

        }
    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        //SET VISABLITY GONE
        pg.setVisibility(View.GONE);

        RecyclerView specialty_list = findViewById(R.id.specialty_list);
        ArrayList<main_screen_list> arrayList = new ArrayList<>();

        Gson gson = new Gson();
        all_specialitiesroot_specialities all_specialitiesroot_specialities = gson.fromJson("" + model.getJsonObject(), all_specialitiesroot_specialities.class);
        all_specialitiesDatumsss = all_specialitiesroot_specialities.getData();

        for (int index = 0; index < all_specialitiesroot_specialities.getData().length; index++) {
            arrayList.add(new main_screen_list(all_specialitiesDatumsss[index].getId(), all_specialitiesDatumsss[index].getTitle(), all_specialitiesDatumsss[index].getImageUrl()));
        }
        utils_adapter utils_adapter = new utils_adapter();
        utils_adapter.griddAdapters(specialty_list, new main_screen_adapter(main_screen.this, arrayList), main_screen.this, 2);
    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mNavigationDrawerFragment.closeDrawer();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //GET DATA FROM SERVER
        try {
            pg.setVisibility(View.VISIBLE);
            new Apicalls(this, this).get_all_specialities();
        } catch (JSONException e) {
            Log.e("WRONG", "WRONG FROM SERVER");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().unregisterNetworkChanges(main_screen.this);
    }
}
