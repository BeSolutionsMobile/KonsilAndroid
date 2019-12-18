package com.besolutions.konsil.compalint_details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.besolutions.konsil.Consulation_result.fragment_message.consulation_list;
import com.besolutions.konsil.R;
import com.besolutions.konsil.utils.utils_adapter;

import java.util.ArrayList;

public class compalint_details extends AppCompatActivity {

    RecyclerView message_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compalint_details);
        get_data();
    }

    void get_data()
    {
        //ADD DATA
        ArrayList<consulation_list> arrayList=new ArrayList<>();
        arrayList.add(new consulation_list("1","Dr Ibraheem","Please send data",null));
        arrayList.add(new consulation_list("1","mahmoud saad","ok",null));
        arrayList.add(new consulation_list("1","Dr Saeed","done mr ",null));

        message_list=(RecyclerView)findViewById(R.id.message_list);
        utils_adapter utils_adapter=new utils_adapter();
        utils_adapter.Adapter(message_list,new message_adapter(this,arrayList),this);
    }
}
