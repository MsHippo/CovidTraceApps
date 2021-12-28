package com.coffee.covidtrace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @SuppressLint("NonConstantResourceId")
    public void SignUp(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_sign_up:
                intent = new Intent(this, LoginActivity.class);
//                intent.putExtra();
                startActivity(intent);
                break;
            case  R.id.btn_to_sign_in:
                intent = new Intent(this, LoginActivity.class);
//                intent.putExtra();
                startActivity(intent);
                break;
        }
    }
}