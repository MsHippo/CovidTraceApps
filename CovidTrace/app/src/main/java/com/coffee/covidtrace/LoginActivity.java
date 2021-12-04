package com.coffee.covidtrace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.coffee.covidtrace.Data.UserDao;
import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.UserEntity;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText username, password;
    Database database;
    UserEntity currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
    }

    @SuppressLint("NonConstantResourceId")
    public void signInActivity(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_sign_in:

                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                if (usernameText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                }else{
                    //query
                    database = Database.getUserDatabase(getApplicationContext());
                    UserDao userDao = database.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(usernameText, passwordText);
                            if (userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                currentUser = userEntity;
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("current user", currentUser);
                                startActivity(intent);
                            }
                        }
                    }).start();
                }
                break;
            case R.id.btn_to_sign_up:
                intent = new Intent(this, SignUpActivity.class);
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