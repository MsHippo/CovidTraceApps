package com.coffee.covidtrace.Ui.healthAssessment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.coffee.covidtrace.Adapter.HealthAssessmentAdapter;
import com.coffee.covidtrace.R;

public class QuestionActivity extends AppCompatActivity {

    String[] questions = {
            "Are you exhibiting 2 or more symptoms as listed below? / Adakah anda mengalami 2 atau lebih gejala berikut?",
            "Are you exhibiting 2 or more symptoms as listed below? / Adakah anda mengalami 2 atau lebih gejala berikut?",
            "Are you exhibiting 2 or more symptoms as listed below? / Adakah anda mengalami 2 atau lebih gejala berikut?",
            "Are you exhibiting 2 or more symptoms as listed below? / Adakah anda mengalami 2 atau lebih gejala berikut?"};
    String[] contents = {
            "aaaaaaaa \naaaaaa \naaaaaaaa \naaaaaaaaa",
            "aaaaaaaa \naaaaaa \naaaaaaaa \naaaaaaaaa",
            "aaaaaaaa \naaaaaa \naaaaaaaa \naaaaaaaaa",
            "aaaaaaaa \naaaaaa \naaaaaaaa \naaaaaaaaa"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_question);

        // my_child_toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Personal Covid-19 Status");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rv_health_assessment);

        // Create adapter passing in the sample user data
        HealthAssessmentAdapter adapter = new HealthAssessmentAdapter(questions, contents);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}