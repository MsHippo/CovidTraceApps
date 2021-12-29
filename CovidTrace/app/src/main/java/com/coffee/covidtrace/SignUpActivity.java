package com.coffee.covidtrace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.coffee.covidtrace.Data.UserDao;
import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.UserEntity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText email, name, nric, phone, password, repass, question, answer;

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
        question = findViewById(R.id.signup_question);
        answer = findViewById(R.id.signup_answer);
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
                userEntity.setQuestion(question.getText().toString());
                userEntity.setAnswer(answer.getText().toString());
                if (validateInput(userEntity)){
                    if (password.getText().toString().equals(Objects.requireNonNull(repass.getText()).toString())){
                        //insert
                        Database database = Database.getDatabase(getApplicationContext());
                        final UserDao userDao = database.userDao();
                        UserEntity userEntity1 = userDao.uniqueEmail(email.getText().toString());
                        if (userEntity1 == null){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //register user
                                userDao.registerUser(userEntity);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),"User Registered", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                                      intent.putExtra();
                                        startActivity(intent);
                                    }
                                });
                            }
                        }).start();
                        }else {
                            Toast.makeText(getApplicationContext(),"Email has been used", Toast.LENGTH_SHORT).show();
                            }
                    }else{
                        Toast.makeText(getApplicationContext(),"Password not match", Toast.LENGTH_SHORT).show();
                    }
                } else{
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
            userEntity.getPassword().isEmpty() ||
            userEntity.getQuestion().isEmpty() ||
            userEntity.getAnswer().isEmpty()){
            return false;
        }
        return true;
    }

}