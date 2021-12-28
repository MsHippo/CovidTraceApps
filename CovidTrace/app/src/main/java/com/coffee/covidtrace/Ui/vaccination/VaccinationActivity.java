package com.coffee.covidtrace.Ui.vaccination;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.ViewModel.SharedViewModel;

import org.w3c.dom.Text;

public class VaccinationActivity extends AppCompatActivity {

    TextView tv_user;
    UserEntity currentUser;
    private SharedViewModel sharedViewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

        // my_child_toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Vaccination");
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
//        ab.setDisplayHomeAsUpEnabled(true);

        tv_user = findViewById(R.id.user_name);

//        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        currentUser = (UserEntity) getIntent().getSerializableExtra("user");
        tv_user.setText("Vaccine for " + currentUser.getName());

    }

    @SuppressLint("NonConstantResourceId")
    public void VaccineRelated(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.vaccine_register:
                intent = new Intent(this, VaccinationRegStatusActivity.class);
                intent.putExtra("user", currentUser);
                startActivity(intent);
                break;
            case R.id.vaccine_info:
                intent = new Intent(this, VaccinationInfoActivity.class);
                startActivity(intent);
                break;
        }
    }

}