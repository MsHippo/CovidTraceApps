package com.coffee.covidtrace.Ui.record;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;

import java.util.HashMap;

public class SuccessCheckInActivity extends AppCompatActivity {

    CardView cardView_upper, cardView_lower, cv_risk_status, cv_vaccination;
    TextView tv_risk_status, tv_vaccine_status;
    UserEntity currentUser;
    History history;
    TextView user_name, user_ic_passport, tv_location_enter, tv_date_enter, tv_time_enter;
    Button btn_done;
    Integer status, vaccine;
    public SuccessCheckViewModel mViewModel;


//    String date, time;
//    Bundle bundle = getIntent().getBundleExtra("SCAN_RESULTS");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_check_in);

        cardView_upper = findViewById(R.id.cv_check_in_upper);
        cardView_lower = findViewById(R.id.cv_check_in_lower);
        user_name = findViewById(R.id.user_name);
        user_ic_passport = findViewById(R.id.user_ic_passport);
        tv_location_enter = findViewById(R.id.tv_location_enter);
        tv_date_enter = findViewById(R.id.tv_date_enter);
        tv_time_enter = findViewById(R.id.tv_time_enter);
        btn_done = findViewById(R.id.btn_done);
        cv_risk_status = findViewById(R.id.cv_risk_status);
        cv_vaccination = findViewById(R.id.cv_vaccination);
        tv_risk_status = findViewById(R.id.tv_risk_status);
        tv_vaccine_status = findViewById(R.id.tv_vaccine_status);

        cardView_upper.setBackgroundResource(R.drawable.check_upper);
        cardView_lower.setBackgroundResource(R.drawable.check_lower);

        currentUser = (UserEntity) getIntent().getSerializableExtra("user");
        history = getIntent().getParcelableExtra("SCAN_RESULTS");


        mViewModel = new ViewModelProvider(this).get(SuccessCheckViewModel.class);


        user_name.setText(currentUser.getName());
        user_ic_passport.setText(currentUser.getEmail());

        tv_location_enter.setText(history.getLocation());
        tv_date_enter.setText(history.getDate());
        tv_time_enter.setText(history.getTime());

        Log.d(TAG, "onCreate: " + currentUser.getName());
        Intent intent = getIntent();
//        HashMap<String, String> hashMap = (HashMap<String, String>)intent.getSerializableExtra("SCAN_RESULTS");

        btn_done.setOnClickListener(v -> finish());

        mViewModel.getUserStatus(currentUser.getId()).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                status = integer;
                Log.d(TAG, "onCreateView: status " + status);
                if (status == 0){
                    cv_risk_status.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.darkGrey));

                    tv_risk_status.setText(R.string.unknown_status);

                } else if (status == 1){
                    cv_risk_status.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.skyBlue));

                    tv_risk_status.setText(R.string.no_symptom_low_risk);

                } else if (status == 2){
                    cv_risk_status.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.darkYellow));

                    tv_risk_status.setText(R.string.medium_symptom);

                }else{
                    cv_risk_status.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red177));

                    tv_risk_status.setText(R.string.high_symptom);

                }
            }
        });

        mViewModel.getUserVaccine(currentUser.getId()).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                vaccine = integer;
                Log.d(TAG, "onCreateView: vaccine " + vaccine);
                if (vaccine == 0){
                    cv_vaccination.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red177));

                    tv_vaccine_status.setText(R.string.vaccination_status0);

                } else if (vaccine == 1){
                    cv_vaccination.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.darkYellow));

                    tv_vaccine_status.setText(R.string.halfly_vaccinated);

                } else if (vaccine == 2){
                    cv_vaccination.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.grassGreen));

                    tv_vaccine_status.setText(R.string.fully_vaccinated);

                }
            }
        });
    }
}