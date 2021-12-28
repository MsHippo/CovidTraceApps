package com.coffee.covidtrace.Ui.history;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.Repository.HistoryRepository;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private HistoryRepository repository;
    private LiveData<List<History>> allHistory;
//    History history;
    int user_id ;


    public HistoryViewModel(@NonNull Application application) {
        super(application);
        repository = new HistoryRepository(application);
        allHistory = repository.getAllHistory(user_id);
    }

    // pass the allHistory list to the activity that invokes it
    public LiveData<List<History>> getAllHistory(int id) {
        return repository.getAllHistory(id);
    }

}