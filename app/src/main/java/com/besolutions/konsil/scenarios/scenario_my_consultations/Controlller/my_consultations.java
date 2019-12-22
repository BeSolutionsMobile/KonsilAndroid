package com.besolutions.konsil.scenarios.scenario_my_consultations.Controlller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_my_consultations.pattern.my_consultations_adapter;
import com.besolutions.konsil.scenarios.scenario_my_consultations.model.my_consultations_list;
import com.besolutions.konsil.utils.utils_adapter;

import java.util.ArrayList;

public class my_consultations extends AppCompatActivity {
    RecyclerView my_consitutauin_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_consultations);
        get_data();
    }

    void get_data()
    {
        //ADD TO ARRAYLIST
        ArrayList<my_consultations_list>arrayList=new ArrayList<>();
        arrayList.add(new my_consultations_list("Dr Mahmoud","Online conversatation","30","open",null,null));
        arrayList.add(new my_consultations_list("Dr Mahmoud","Online conversatation","30","open",null,null));
        arrayList.add(new my_consultations_list("Dr Mahmoud","Online conversatation","30","open",null,null));

        my_consitutauin_list=(RecyclerView)findViewById(R.id.my_consitutauin_list);
        utils_adapter utils_adapter=new utils_adapter();
        utils_adapter.Adapter(my_consitutauin_list,new my_consultations_adapter(this,arrayList),this);
    }
}
