package com.coffee.covidtrace.Ui.record;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Repository.HistoryRepository;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {
    private HistoryRepository repository;
    private LiveData<List<History>> allHistory;

    public RecordViewModel(@NonNull Application application) {
        super(application);
        repository = new HistoryRepository(application);
        allHistory = repository.getAllHistory();
    }

    public void insert(History history) {
//        HistoryRepository repository = null;
        repository.insert(history);
    }

    // pass the allHistory list to the activity that invokes it
    public LiveData<List<History>> getAllHistory() {
        return allHistory;
    }
}