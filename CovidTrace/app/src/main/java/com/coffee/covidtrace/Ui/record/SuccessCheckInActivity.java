package com.coffee.covidtrace.Ui.record;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

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

    CardView cardView_upper, cardView_lower;
    UserEntity currentUser;
    History history;
    TextView user_name, user_ic_passport, tv_location_enter, tv_date_enter, tv_time_enter;
    Button btn_done;
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

        cardView_upper.setBackgroundResource(R.drawable.check_upper);
        cardView_lower.setBackgroundResource(R.drawable.check_lower);

        currentUser = (UserEntity) getIntent().getSerializableExtra("user");
        history = getIntent().getParcelableExtra("SCAN_RESULTS");



        user_name.setText(currentUser.getName());
        user_ic_passport.setText(currentUser.getEmail());

        tv_location_enter.setText(history.getLocation());
        tv_date_enter.setText(history.getDate());
        tv_time_enter.setText(history.getTime());

        Log.d(TAG, "onCreate: " + currentUser.getName());
        Intent intent = getIntent();
//        HashMap<String, String> hashMap = (HashMap<String, String>)intent.getSerializableExtra("SCAN_RESULTS");

        btn_done.setOnClickListener(v -> finish());
    }
}