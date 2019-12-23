package com.besolutions.konsil.scenarios.scenario_mian_page.Controller;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.besolutions.konsil.NavigationDrawerCallbacks;
import com.besolutions.konsil.NavigationDrawerFragment;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_be_a_doctor.Controller.be_a_doctor;
import com.besolutions.konsil.scenarios.scenario_mian_page.model.main_screen_list;
import com.besolutions.konsil.scenarios.scenario_mian_page.pattern.main_screen_adapter;
import com.besolutions.konsil.scenarios.scenario_my_consultations.Controlller.my_consultations;
import com.besolutions.konsil.scenarios.scenario_personal_info.Controller.personal_info;
import com.besolutions.konsil.scenarios.scenarios_faq.controller.faq;
import com.besolutions.konsil.utils.utils_adapter;

import java.util.ArrayList;

public class main_screen extends AppCompatActivity implements NavigationDrawerCallbacks {
    Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    int num=0;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
     //   mToolbar.setTitle("Spechalist");
        setSupportActionBar(mToolbar);

        title=(TextView)findViewById(R.id.title);
        title.setText("Spechalist");

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        get_data();
    }

    void get_data()
    {
        RecyclerView specialty_list=(RecyclerView)findViewById(R.id.specialty_list);
        //ADD TO LIST
        ArrayList<main_screen_list>arrayList=new ArrayList<>();
        arrayList.add(new main_screen_list("1","Surgery",null));
        arrayList.add(new main_screen_list("1","Surgery",null));
        arrayList.add(new main_screen_list("1","Surgery",null));
        arrayList.add(new main_screen_list("1","Surgery",null));
        arrayList.add(new main_screen_list("1","Surgery",null));
        arrayList.add(new main_screen_list("1","Surgery",null));

        utils_adapter utils_adapter=new utils_adapter();
        utils_adapter.griddAdapters(specialty_list,new main_screen_adapter(this,arrayList),this,2);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        // update the main content by replacing fragments
        if(position==0)
        {
            if(num==0)
            {
                num=1;
            }
            else {
                startActivity(new Intent(this, personal_info.class));
            }
        }
        else if(position==1)
        {
            startActivity(new Intent(this, my_consultations.class));
        }
        else if(position==2)
        {
            startActivity(new Intent(this, faq.class));
        }
        else if(position==5)
        {
            startActivity(new Intent(this, be_a_doctor.class));
        }

    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
