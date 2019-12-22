package com.besolutions.konsil.scenarios.scenario_doctor_list.Controller;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.besolutions.konsil.NavigationDrawerCallbacks;
import com.besolutions.konsil.NavigationDrawerFragment;
import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.filter;
import com.besolutions.konsil.scenarios.scenario_doctor_list.pattern.doctor_adapter;
import com.besolutions.konsil.scenarios.scenario_doctor_list.model.doctor_list_items;
import com.besolutions.konsil.scenarios.scenario_my_consultations.Controlller.my_consultations;
import com.besolutions.konsil.scenarios.scenario_personal_info.Controller.personal_info;
import com.besolutions.konsil.scenarios.scenarios_faq.controller.faq;
import com.besolutions.konsil.utils.utils_adapter;

import java.util.ArrayList;

public class doctor_list extends AppCompatActivity implements NavigationDrawerCallbacks, View.OnClickListener {
    RecyclerView doctor_list;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    TextView filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list);

        filter=(TextView)findViewById(R.id.filter);
        filter.setOnClickListener(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mToolbar.setTitle("Doctor List");
        setSupportActionBar(mToolbar);


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        get_data();
    }

    void  get_data()
    {

        //ADD TO ARRAY LIST
        ArrayList<doctor_list_items> arrayList=new ArrayList<>();
        arrayList.add(new doctor_list_items("1","ahmed awad","prof","dddd",5));
        arrayList.add(new doctor_list_items("1","mahmoud awad","prof","dddd",3));
        arrayList.add(new doctor_list_items("1","mahmoud awad","prof","dddd",3));
        arrayList.add(new doctor_list_items("1","mahmoud awad","prof","dddd",3));
        arrayList.add(new doctor_list_items("1","mahmoud awad","prof","dddd",3));

        doctor_list=(RecyclerView)findViewById(R.id.doctor_list);

        utils_adapter utils_adapter = new utils_adapter();
        utils_adapter.Adapter(doctor_list,new doctor_adapter(arrayList,this),this);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
       if(position==0)
       {
           startActivity(new Intent(this, personal_info.class));
       }
       else if(position==1)
       {
           startActivity(new Intent(this, my_consultations.class));
       }
       else if(position==2)
       {
           startActivity(new Intent(this, faq.class));
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


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.filter)
        {
            com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.filter filter=new filter();
            filter.dialog(this,R.layout.doctor_filter,.90);
        }
    }
}
