package com.coffee.covidtrace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coffee.covidtrace.Ui.healthAssessment.HealthStartActivity;
import com.coffee.covidtrace.Ui.healthAssessment.QuestionActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @SuppressLint("NonConstantResourceId")
    public void signInActivity(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_sign_in:
                intent = new Intent(this, MainActivity.class);
//                intent.putExtra();
                startActivity(intent);
                break;
            case R.id.btn_to_sign_up:
                intent = new Intent(this, SignUpActivity.class);
//                intent.putExtra();
                startActivity(intent);
                break;
            case R.id.btn_forget_pass:
                intent = new Intent(this, ChangePasswordActivity.class);
//                intent.putExtra();
                startActivity(intent);
                break;

        }
    }
}