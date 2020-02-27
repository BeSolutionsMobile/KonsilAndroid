package com.besolutions.konsil.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class utils_adapter {

    /**
     *GRIDVIEW Adapter
     */
    public void griddAdapters(RecyclerView recyclerView, RecyclerView.Adapter dd, Context context, int num)
    {
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(context,num);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter= dd;
        recyclerView.setAdapter(adapter);
    }

    /**
     *List Adapter
     */
    public void Adapter(RecyclerView recyclerView, RecyclerView.Adapter dd, Context context)
    {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter= dd;
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    /**
     *Horozintal Adapter
     */
    public void Horozintal(RecyclerView recyclerView, RecyclerView.Adapter dd, Context context)
    {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter= dd;
        recyclerView.setAdapter(adapter);
    }
}
