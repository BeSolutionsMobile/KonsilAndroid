package com.besolutions.konsil.scenarios.scenario_terms_of_use.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.besolutions.konsil.R;

public class terms_of_use extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_of_use);

        //TITLE IS TERMS OF USE
        TextView title = findViewById(R.id.title);
        String terms_of_use = getResources().getString(R.string.terms_of_use);
        title.setText(terms_of_use);
    }
}
