package com.coffee.covidtrace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.coffee.covidtrace.Data.UserDao;
import com.coffee.covidtrace.Data.UserDatabase;
import com.coffee.covidtrace.Data.UserEntity;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText email, name, nric, phone, password, repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.signup_email);
        name = findViewById(R.id.signup_name);
        nric = findViewById(R.id.signup_nric);
        phone = findViewById(R.id.signup_phone);
        password = findViewById(R.id.singup_password);
        repass = findViewById(R.id.signup_repass);
    }

    @SuppressLint("NonConstantResourceId")
    public void SignUp(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_sign_up:

                //create user entity
                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(email.getText().toString());
                userEntity.setName(name.getText().toString());
                userEntity.setNric(nric.getText().toString());
                userEntity.setPhone(phone.getText().toString());
                userEntity.setPassword(password.getText().toString());
                if (validateInput(userEntity)){
                    if (password.getText().toString() == repass.getText().toString()){
                        //insert
                        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                        final UserDao userDao = userDatabase.userDao();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //register user
                                userDao.registerUser(userEntity);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),"User Registered", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), AuthenticationActivity.class);
//                                      intent.putExtra();
                                        startActivity(intent);
                                    }
                                });
                            }
                        }).start();
                    }else{
                        Toast.makeText(getApplicationContext(),"Password not match", Toast.LENGTH_SHORT).show();
                    } } else{
                    Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                break;
            case  R.id.btn_to_sign_in:
                intent = new Intent(this, LoginActivity.class);
//                intent.putExtra();
                startActivity(intent);
                break;
        }
    }

    private Boolean validateInput(UserEntity userEntity){
        if (userEntity.getEmail().isEmpty() ||
            userEntity.getName().isEmpty() ||
            userEntity.getNric().isEmpty() ||
            userEntity.getPassword().isEmpty()){
            return false;
        }
        return true;
    }

}