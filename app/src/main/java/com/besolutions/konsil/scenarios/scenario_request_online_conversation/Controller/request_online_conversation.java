package com.besolutions.konsil.scenarios.scenario_request_online_conversation.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.online_conversation;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.pattern.conversation_item_adapter;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.model.conversation_reserv_list;
import com.besolutions.konsil.utils.utils_adapter;

import java.util.ArrayList;

public class request_online_conversation extends AppCompatActivity implements View.OnClickListener {
     RecyclerView reserv_list;
     Button complete_req;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_online_conversation);
        complete_req=(Button)findViewById(R.id.complete_req);
        complete_req.setOnClickListener(this);
        get_data();

        set_toolbar_name();
    }

    void get_data()
    {
        reserv_list=(RecyclerView)findViewById(R.id.reserv_list);

        ArrayList<conversation_reserv_list> arrayList=new ArrayList<>();
        arrayList.add(new conversation_reserv_list("1","From 1 Am to 2 Pm"));
        arrayList.add(new conversation_reserv_list("1","From 1 Am to 2 Pm"));
        arrayList.add(new conversation_reserv_list("1","From 1 Am to 2 Pm"));

        utils_adapter utils_adapter=new utils_adapter();
        utils_adapter.Adapter(reserv_list,new conversation_item_adapter(this,arrayList),this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.complete_req)
        {
          startActivity(new Intent(this, online_conversation.class));
        }
    }

    public void set_toolbar_name()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        TextView title=(TextView)findViewById(R.id.title);
        String r_o_c = getResources().getString(R.string.request_online_converstaion);
        title.setText(r_o_c);
    }
}
