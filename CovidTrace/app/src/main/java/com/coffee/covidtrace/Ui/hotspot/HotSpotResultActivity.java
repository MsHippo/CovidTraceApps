//package com.coffee.covidtrace.Ui.hotspot;
//
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.os.Bundle;
//
//import com.coffee.covidtrace.R;
//
//public class HotSpotResultActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hot_spot_result);
//
//        // my_child_toolbar is defined in the layout file
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("COVID-19 Hotspot Tracker");
//        setSupportActionBar(toolbar);
//
//        // Get a support ActionBar corresponding to this toolbar
//        ActionBar ab = getSupportActionBar();
//
//        // Enable the Up button
//        assert ab != null;
//        ab.setDisplayHomeAsUpEnabled(true);
//    }
//}