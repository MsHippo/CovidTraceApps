package com.coffee.covidtrace.Ui.vaccination;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.coffee.covidtrace.R;

import java.util.Objects;

public class VaccinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

        // my_child_toolbar is defined in the layout file
        Toolbar toolbar =
                (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void vaccine_related(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.vaccine_register:
                intent = new Intent(this, VaccinationRegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.vaccine_info:
                intent = new Intent(this, VaccinationInfoActivity.class);
                startActivity(intent);
                break;
        }
    }

}