package com.coffee.covidtrace.Ui.healthAssessment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coffee.covidtrace.Adapter.HealthAssessmentAdapter;
import com.coffee.covidtrace.Data.HealthAssessment;
import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Repository.HealthAssessmentRepository;
import com.coffee.covidtrace.Repository.HistoryRepository;
import com.coffee.covidtrace.Ui.history.HistoryViewModel;
import com.coffee.covidtrace.ViewModel.SharedViewModel;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    String[] questions = {
            "Are you exhibiting 2 or more symptoms as listed below? / Adakah anda mengalami 2 atau lebih gejala berikut?",
            "Are you exhibiting 2 or more symptoms as listed below? / Adakah anda mengalami 2 atau lebih gejala berikut?",
            "Are you exhibiting 2 or more symptoms as listed below? / Adakah anda mengalami 2 atau lebih gejala berikut?",
            "Are you exhibiting 2 or more symptoms as listed below? / Adakah anda mengalami 2 atau lebih gejala berikut?"};
    String[] contents = {
            "aaaaaaaa \naaaaaa \naaaaaaaa \naaaaaaaaa",
            "aaaaaaaa \naaaaaa \naaaaaaaa \naaaaaaaaa",
            "aaaaaaaa \naaaaaa \naaaaaaaa \naaaaaaaaa",
            "aaaaaaaa \naaaaaa \naaaaaaaa \naaaaaaaaa"};

    UserEntity currentUser;

    private TextView tvQuestion, tvQuestionCount, tvQuestionContent;
    private RadioGroup rbGroup;
    private RadioButton rb1, rb2;
    private Button buttonNext;

    private LinkedList<HealthAssessment> questionList = new LinkedList<>();
    private int questionCounter;
    private int questionCountTotal;
    private HealthAssessment currentQuestion;

    private QuestionViewModel questionViewModel;
    Boolean added = false;
    //check if the question is answered or not
    private boolean answered = true;

    private static final String TAG = QuestionActivity.class.getSimpleName();

    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_question);

        // my_child_toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Personal Covid-19 Status");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
//        ab.setDisplayHomeAsUpEnabled(true);

//        // Lookup the recyclerview in activity layout
//        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rv_health_assessment);
//
//        // Create adapter passing in the sample user data
//        HealthAssessmentAdapter adapter = new HealthAssessmentAdapter(questions, contents);
//        // Attach the adapter to the recyclerview to populate items
//        rvContacts.setAdapter(adapter);
//        // Set layout manager to position the items
//        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        currentUser = (UserEntity) getIntent().getSerializableExtra("user");
        tvQuestion = findViewById(R.id.question_title);
        tvQuestionCount = findViewById(R.id.total_question);
        tvQuestionContent = findViewById(R.id.question_content);

        rbGroup = findViewById(R.id.radioGroup_option);
        rb1 = findViewById(R.id.radio_button_1);
        rb2 = findViewById(R.id.radio_button_2);

        buttonNext = findViewById(R.id.btn_next);


        questionViewModel = new ViewModelProvider(this)
                .get(QuestionViewModel.class);

        questionList.addLast(currentQuestion);

        rbGroup.clearCheck();
        showNextQuestion();



        //when clicking the button next, to next question
        buttonNext.setOnClickListener(this);


//        repository.getAllAssessment().observe(this, new Observer<List<HealthAssessment>>() {
//            @Override
//            public void onChanged(List<HealthAssessment> assessmentList) {
//                currentQuestion = assessmentList.get(questionCounter);
//            }
//        });
    }

    private void showNextQuestion() {

        questionViewModel.getAllAssessment().observe(this, new Observer<List<HealthAssessment>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(List<HealthAssessment> assessmentList) {
                questionCountTotal = assessmentList.size();
//                currentQuestion = assessmentList.get(questionCounter);

                Log.d(TAG, "showNextQuestion: Total: " + questionCountTotal);
                Log.d(TAG, "showNextQuestion: Every: " + questionCounter);

//        if have questions to answer
                if (questionCountTotal == 0) {
                    Toast.makeText(
                            QuestionActivity.this,
                            "No question. Please come back latter. Thanks.",
                            Toast.LENGTH_LONG
                    ).show();
                    finish();
                } else if (questionCounter < questionCountTotal) {
//            currentQuestion = questionList.get(questionCounter);
                currentQuestion = assessmentList.get(questionCounter);

                tvQuestion.setText(currentQuestion.getQuestion());
                tvQuestionCount.setText("Question " + (questionCounter + 1));
                tvQuestionContent.setText(currentQuestion.getQuestion_content());

                rb1.setText(currentQuestion.getOption1());
                rb2.setText(currentQuestion.getOption2());

                answered = false;
                } else {
                    submitAssessment();
                }
            }
        });

    }

    private void submitAssessment() {
        Intent i = new Intent(QuestionActivity.this, SubmitActivity.class);
        i.putExtra("user", currentUser);
        i.putExtra("counter", counter);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_next) {
            added = false;
            if (!answered) {
                if (rb1.isChecked() || rb2.isChecked()) {
                    checkAnswer();
                    rbGroup.clearCheck();
                    questionCounter++;
                    showNextQuestion();

                } else {
                    Toast.makeText(
                            QuestionActivity.this,
                            "Please select an option",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        }
    }

    private void checkAnswer() {
        //in the case of radio button of "agree" is chosen
        if (rb1.isChecked()) {
            //1 equals to agree
            Log.d(TAG, "Selected No"); //text show in console to double check

        }else if (rb2.isChecked()) {
            Log.d(TAG, "Selected Yes");
            counter ++;
        }
    }
}