package com.besolutions.konsil.scenarios.scenario_policy.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.besolutions.konsil.R;

public class policy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.policy);

        //TITLE IS PRIVACY
        TextView title = findViewById(R.id.title);
        String terms_of_use = getResources().getString(R.string.privacy);
        title.setText(terms_of_use);
    }
}
