package com.coffee.covidtrace.Ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Adapter.ReportAdapter;
import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.ReportCase;
import com.coffee.covidtrace.Repository.HistoryRepository;
import com.coffee.covidtrace.Repository.ReportCaseRepository;

import java.util.List;

public class ReportCaseViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private ReportCaseRepository repository;
    private LiveData<List<ReportCase>> allReport;
    int user_id ;


    public ReportCaseViewModel(@NonNull Application application) {
        super(application);
        repository = new ReportCaseRepository(application);
        allReport = repository.getAllCases(user_id);
    }

    // pass the allHistory list to the activity that invokes it
    public LiveData<List<ReportCase>> getAllCases(int id) {
        return repository.getAllCases(id);
    }

}