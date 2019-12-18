package com.besolutions.konsil.faq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.besolutions.konsil.R;

import java.util.ArrayList;

public class faq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);

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



        mylsits.add(new expandlist(1,"Whats your name?",about));
        about.add(new itemList(1,"My name is Hatly"));

        mylsits.add(new expandlist(1,"Whats your name of your best friend?",about1));
        about1.add(new itemList(1,"his name is Hatly"));

        mylsits.add(new expandlist(1,"Whats your name of your best friend?",about2));
        about2.add(new itemList(1,"his name is Hatly"));

        mylsits.add(new expandlist(1,"Whats your name of your best friend?",about3));
        about3.add(new itemList(1,"his name is Hatly"));

        mylsits.add(new expandlist(1,"Whats your name of your best friend?",about4));
        about4.add(new itemList(1,"his name is Hatly"));

        mylsits.add(new expandlist(1,"Whats your name of your best friend?",about5));
        about5.add(new itemList(1,"his name is Hatly"));

        mylsits.add(new expandlist(1,"Whats your name of your best friend?",about6));
        about6.add(new itemList(1,"his name is Hatly"));

        mylsits.add(new expandlist(1,"Whats your name of your best friend?",about7));
        about7.add(new itemList(1,"his name is Hatly"));

        mylsits.add(new expandlist(1,"Whats your name of your best friend?",about8));
        about8.add(new itemList(1,"his name is Hatly"));



        RecyclerView.LayoutManager layoutManagerr=new LinearLayoutManager(this);
        recyclerViewexpand.setLayoutManager(layoutManagerr);
        RecyclerView.Adapter adapterr=new expandAdapter(mylsits,faq.this);
        recyclerViewexpand.setAdapter(adapterr);

    }
}
