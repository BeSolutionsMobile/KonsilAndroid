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

public class doctor_list extends AppCompatActivity implements View.OnClickListener {
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
        setSupportActionBar(mToolbar);

        TextView title=(TextView)findViewById(R.id.title);
        String doc_filters = getResources().getString(R.string.Specialist);
        title.setText(doc_filters);



        get_data();
    }

    void  get_data()
    {

        //ADD TO ARRAY LIST
        ArrayList<doctor_list_items> arrayList=new ArrayList<>();
        arrayList.add(new doctor_list_items("1","ahmed awad",getString(R.string.doctor),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfxzJ84GqW4XD_88NeeOonmUWr1seByDHl0WJkYqk05DVlInfZ&s",5));
        arrayList.add(new doctor_list_items("1","mahmoud ibraheem",getString(R.string.doctor),"https://www.thehealthy.com/wp-content/uploads/2017/09/02_doctor_Insider-Tips-to-Choosing-the-Best-Primary-Care-Doctor_519507367_Stokkete.jpg",3));
        arrayList.add(new doctor_list_items("1","saeed awad",getString(R.string.doctor),"https://img.medscape.com/thumbnail_library/dt_140605_serious_male_doctor_hospital_800x600.jpg",3));
        arrayList.add(new doctor_list_items("1","mahmoud el ahmady",getString(R.string.doctor),"https://metrosource.com/wp-content/uploads/2018/05/lgbt-friendly-doctor.jpg",3));
        arrayList.add(new doctor_list_items("1","fathi yaseen awad",getString(R.string.doctor),"https://img.medscape.com/thumbnail_library/dt_181213_sad_depressed_pensive_doctor_800x450.jpg",3));

        doctor_list=(RecyclerView)findViewById(R.id.doctor_list);

        utils_adapter utils_adapter = new utils_adapter();
        utils_adapter.Adapter(doctor_list,new doctor_adapter(arrayList,this),this);

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
