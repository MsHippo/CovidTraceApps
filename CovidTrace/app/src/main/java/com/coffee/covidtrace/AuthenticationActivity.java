package com.coffee.covidtrace;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.UserDao;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.ViewModel.SharedViewModel;

import org.w3c.dom.Text;

public class AuthenticationActivity extends AppCompatActivity {

    Button btn_verify;
    TextView question;
    EditText answer;
    Database database;
    UserEntity currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        question = findViewById(R.id.security_question);
        answer = findViewById(R.id.answer);

        Intent intent = getIntent();
        String email_txt = intent.getStringExtra("email");

        database = Database.getDatabase(getApplicationContext());
        UserDao userDao = database.userDao();
        String question_txt = userDao.findQuestion(email_txt);
        question.setText(question_txt);

        // toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Authentication");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        btn_verify = findViewById(R.id.btn_verify);
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer_txt = answer.getText().toString();
                if (answer_txt.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your answer", Toast.LENGTH_SHORT).show();
                }else{
                    //database = Database.getDatabase(getApplicationContext());
                    //UserDao userDao = database.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.authentication(email_txt, answer_txt);
                            if (userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }else{
                                currentUser = userEntity;
                                Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
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