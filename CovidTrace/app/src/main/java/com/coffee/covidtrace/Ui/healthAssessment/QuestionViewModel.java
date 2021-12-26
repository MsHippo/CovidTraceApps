package com.coffee.covidtrace.Ui.healthAssessment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Data.HealthAssessment;
import com.coffee.covidtrace.Repository.HealthAssessmentRepository;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private HealthAssessmentRepository healthAssessmentRepository;
    private LiveData<List<HealthAssessment>> allAssessment;

    public QuestionViewModel(@NonNull Application application) {
        super(application);
        healthAssessmentRepository = new HealthAssessmentRepository(application);
        allAssessment = healthAssessmentRepository.getAllAssessment();
    }

    public LiveData<List<HealthAssessment>>getAllAssessment(){
        return allAssessment;
    }
}
