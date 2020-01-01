package com.besolutions.konsil.scenarios.scenario_splash_screen.pattern;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_login.Controller.login;
import com.besolutions.konsil.scenarios.scenario_splash_screen.Controller.splash_screen;
import com.squareup.picasso.Picasso;

public class viewimage extends PagerAdapter implements View.OnClickListener {
    Context context;

    public viewimage(Context context)
    {
        this.context=context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==(LinearLayout)o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {


        View view = null;

        if(position==0)
        {
            view= LayoutInflater.from(context).inflate(R.layout.f_layout_tour,container,false);
        } else if(position==1)
        {
            view= LayoutInflater.from(context).inflate(R.layout.s_layout_tour,container,false);

            TextView skip=(TextView)view.findViewById(R.id.skip);
            skip.setOnClickListener(this);

        }
        else if(position==2)
        {
            view= LayoutInflater.from(context).inflate(R.layout.t_layout_tour,container,false);

            Button start=(Button)view.findViewById(R.id.start);
            TextView skip=(TextView)view.findViewById(R.id.skip);

            skip.setOnClickListener(this);
            start.setOnClickListener(this);

        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {
            context.startActivity(new Intent(context, login.class));
    }
}