package com.besolutions.konsil.scenarios.scenarios_faq.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenarios_faq.pattern.expandAdapter;
import com.besolutions.konsil.scenarios.scenarios_faq.pattern.expandlist;
import com.besolutions.konsil.scenarios.scenarios_faq.model.itemList;

import java.util.ArrayList;

public class faq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);

        set_toolbar_name();

        RecyclerView recyclerViewexpand=(RecyclerView)findViewById(R.id.recycle);

        ArrayList<expandlist> mylsits=new ArrayList();

        ArrayList<itemList>about=new ArrayList();
        ArrayList<itemList>about1=new ArrayList();
        ArrayList<itemList>about2=new ArrayList();
        ArrayList<itemList>about3=new ArrayList();
        ArrayList<itemList>about4=new ArrayList();
        ArrayList<itemList>about5=new ArrayList();
        ArrayList<itemList>about6=new ArrayList();
        ArrayList<itemList>about7=new ArrayList();
        ArrayList<itemList>about8=new ArrayList();



        mylsits.add(new expandlist(1,"Whats application name?",about));
        about.add(new itemList(1,"application is called konzil"));

        mylsits.add(new expandlist(1,"why konzil",about1));
        about1.add(new itemList(1,"you can find best doctors with best prices"));

        mylsits.add(new expandlist(1,"how many doctors in konzil",about2));
        about2.add(new itemList(1,"knzil collect more than 1000 doctors"));

        mylsits.add(new expandlist(1,"where is konzil",about3));
        about3.add(new itemList(1,"its is in germany "));

        mylsits.add(new expandlist(1,"what about our social media accounts",about4));
        about4.add(new itemList(1,"we have facebook and twitter"));




        RecyclerView.LayoutManager layoutManagerr=new LinearLayoutManager(this);
        recyclerViewexpand.setLayoutManager(layoutManagerr);
        RecyclerView.Adapter adapterr=new expandAdapter(mylsits,faq.this);
        recyclerViewexpand.setAdapter(adapterr);

    }

    public void set_toolbar_name()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title=(TextView)findViewById(R.id.title);
        title.setText("FAQ");
    }
}
