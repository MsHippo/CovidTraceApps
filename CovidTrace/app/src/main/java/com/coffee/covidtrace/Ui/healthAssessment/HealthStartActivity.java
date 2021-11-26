package com.coffee.covidtrace.Ui.healthAssessment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coffee.covidtrace.Adapter.HistoryAdapter;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.ReportCaseActivity;

public class HealthStartActivity extends AppCompatActivity {

    Button btn_start_health_assessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_start);

        // my_child_toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Personal Covid-19 Status");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        btn_start_health_assessment = findViewById(R.id.btn_start_health_assessment);
        btn_start_health_assessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthStartActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });



    }
}