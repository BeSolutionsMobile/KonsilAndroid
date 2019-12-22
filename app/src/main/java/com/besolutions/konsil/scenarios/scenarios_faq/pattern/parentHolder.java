package com.besolutions.konsil.scenarios.scenarios_faq.pattern;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.besolutions.konsil.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class parentHolder extends GroupViewHolder {
    TextView title;
    RelativeLayout button;

    public parentHolder(View itemView) {
        super(itemView);
        title= itemView.findViewById(R.id.parent);
      //  button = itemView.findViewById(R.id.button);

    }

    public void setGenreName(String name){
        title.setText(name);
    }
    public void setArtistName(int img){

    }}
