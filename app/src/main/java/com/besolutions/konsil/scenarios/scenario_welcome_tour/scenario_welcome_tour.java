package com.besolutions.konsil.scenarios.scenario_welcome_tour;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.besolutions.konsil.R;

public class scenario_welcome_tour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenario_welcome_tour);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
