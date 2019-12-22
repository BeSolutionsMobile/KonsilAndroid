package com.besolutions.konsil.scenarios.scenarios_faq.pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenarios_faq.model.itemList;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class expandAdapter extends ExpandableRecyclerViewAdapter<parentHolder,childHolder> {

    ArrayList<expandlist>mylist;
    Context context;



    public expandAdapter(List<? extends ExpandableGroup> groups, Context context) {
        super(groups);
        this.context=context;
    }

    @Override
    public parentHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item,parent,false);
        return new parentHolder(view);
    }

    @Override
    public childHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_items_without_child,parent,false);
        return new childHolder(view);    }

    @Override
    public void onBindChildViewHolder(childHolder holder, int flatPosition, ExpandableGroup group, final int childIndex) {
        itemList datas=(itemList)group.getItems().get(childIndex);
        holder.setArtistName(datas.getName());
        final int id=datas.getId();


    }

    @Override
    public void onBindGroupViewHolder(parentHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGenreName(group.getTitle());




    }


}
