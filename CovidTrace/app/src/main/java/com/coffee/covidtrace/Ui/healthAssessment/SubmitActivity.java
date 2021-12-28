package com.coffee.covidtrace.Ui.healthAssessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.UserDao;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.MainActivity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.home.HomeFragment;

public class SubmitActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_cancel, btn_submit;
    UserEntity currentUser;
    ProgressBar progressBar;
    int counter;
    private static final String TAG = SubmitActivity.class.getSimpleName();
//    Database database;
//    UserDao userDao;
    int low = 1;
    int medium = 2;
    int high = 3;
    private SubmitViewModel submitViewModel;

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
        counter = getIntent().getIntExtra("counter", 0);


        Log.d(TAG, "onCreate: counter " + counter);
        if (currentUser.getId()!=null){
            Log.d(TAG, "onCreate: user id " + currentUser.getId());
        }

        submitViewModel = new ViewModelProvider(this)
                .get(SubmitViewModel.class);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        int loading_time = 2000;
//        database = Database.getDatabase(getApplicationContext());
//        userDao = database.userDao();

        switch (v.getId()) {
            case R.id.btn_cancel:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("user", currentUser);
                Toast.makeText(SubmitActivity.this, "Cancelled submission", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
            case R.id.btn_submit:
                intent = new Intent(this, MainActivity.class);
                if (counter == 0){
                    submitViewModel.insert(low, currentUser.getId());
                } else if (counter >= 2){
                    submitViewModel.insert(high, currentUser.getId());
                } else{
                    submitViewModel.insert(medium, currentUser.getId());
                }

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