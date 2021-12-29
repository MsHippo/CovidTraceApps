package com.coffee.covidtrace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.UserDao;
import com.coffee.covidtrace.Data.UserEntity;

public class FindAccount extends AppCompatActivity {

    EditText email;
    Button verify;
    Database database;
    UserEntity currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_account);

        email = findViewById(R.id.findaccount_email);
        verify = findViewById(R.id.btn_findaccount_verify);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_txt = email.getText().toString();
                if (email_txt.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                }else{
                    database = Database.getDatabase(getApplicationContext());
                    UserDao userDao = database.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.findAccount(email_txt);
                            if (userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }else{
                                currentUser = userEntity;
                                Log.d("login", String.valueOf(currentUser.getName()));
                                Log.d("login", String.valueOf(currentUser.getId()));
                                Intent intent = new Intent(getApplicationContext(), AuthenticationActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("user", currentUser);
                                intent.putExtra("email", email_txt);
                                startActivity(intent);
                            }
                        }
                    }).start();
                }

            }
        });


    }
}