package com.besolutions.konsil.scenarios.scenario_mian_page.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_mian_page.model.main_screen_list;
import com.besolutions.konsil.scenarios.scenario_mian_page.pattern.main_screen_adapter;
import com.besolutions.konsil.utils.utils_adapter;

import java.util.ArrayList;

public class main_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

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
}
