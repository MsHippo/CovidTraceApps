package com.coffee.covidtrace.Ui.healthAssessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;

public class SubmitActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_cancel, btn_submit;
    UserEntity currentUser;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_submit = findViewById(R.id.btn_submit);
        progressBar = findViewById(R.id.progress_bar);

        btn_cancel.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        currentUser = (UserEntity) getIntent().getSerializableExtra("user");

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        int loading_time = 3000;

        switch (v.getId()) {
            case R.id.btn_cancel:
                intent = new Intent(this, HealthStartActivity.class);
                Toast.makeText(SubmitActivity.this, "Cancelled submission", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
            case R.id.btn_submit:
                intent = new Intent(this, HealthStartActivity.class);
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        intent.putExtra("user", currentUser);
                        Toast.makeText(SubmitActivity.this, "Status Submitted", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }, loading_time);
                break;
        }
    }
}