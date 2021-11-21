package com.coffee.covidtrace.Ui.vaccination;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.profile.ProfileFragment;

public class VaccineRegistrationActivity extends AppCompatActivity {

//    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_registration);
//        cardView = findViewById(R.id.cv_details);
//        cardView.setBackgroundResource(R.drawable.bg_gradient);

        // the toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Register");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @SuppressLint("NonConstantResourceId")
    public void edit_confirm_activity(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_edit_detail:
                //edit the user information
//                intent = new Intent(this, ProfileFragment.class);
//                startActivity(intent);
                break;
            case R.id.btn_confirm_detail:
                intent = new Intent(this, VaccineDetermineLocationActivity.class);
                startActivity(intent);
                break;
        }

    }
}