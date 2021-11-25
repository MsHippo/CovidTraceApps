package com.coffee.covidtrace.Ui.hotspot;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.record.SuccessCheckInActivity;


public class HotspotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot);

        // my_child_toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("COVID-19 Hotspot Tracker");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void searchHotspotArea(View view) {
        Intent intent = new Intent(this, HotSpotResultActivity.class);
//        intent.putExtra("LOCATION_RESULTS", String.valueOf(location));
        startActivity(intent);
    }
}