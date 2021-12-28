package com.coffee.covidtrace.Data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "health_assessment_table")
public class HealthAssessment implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int question_id;
    private String question;
    private String question_content;
    private String option1;
    private String option2;
    private String category;

    public HealthAssessment(String question, String question_content, String option1, String option2, String category) {
        this.question = question;
        this.question_content = question_content;
        this.option1 = option1;
        this.option2 = option2;
        this.category = category;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
