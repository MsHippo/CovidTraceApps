package com.coffee.covidtrace.Ui.record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.coffee.covidtrace.R;

import java.util.HashMap;

public class SuccessCheckInActivity extends AppCompatActivity {

    CardView cardView_upper, cardView_lower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_check_in);

        cardView_upper = findViewById(R.id.cv_check_in_upper);
        cardView_lower = findViewById(R.id.cv_check_in_lower);

        cardView_upper.setBackgroundResource(R.drawable.check_upper);
        cardView_lower.setBackgroundResource(R.drawable.check_lower);

        Intent intent = getIntent();
//        HashMap<String, String> hashMap = (HashMap<String, String>)intent.getSerializableExtra("SCAN_RESULTS");
    }
}