package com.coffee.covidtrace.Ui.record;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.UserDao;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.Repository.HistoryRepository;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {
    private HistoryRepository repository;
    Database database = Database.getDatabase(getApplication());
    UserDao userDao = database.userDao();
    private LiveData<List<History>> allHistory;
    private LiveData<List<History>> lastHistory;
    private LiveData<Integer>status;
    private LiveData<Integer>vaccine;

    //    UserEntity userEntity;
    int user_id;
    public RecordViewModel(@NonNull Application application) {
        super(application);
        repository = new HistoryRepository(application);
        allHistory = repository.getAllHistory(user_id);
        lastHistory = repository.getLatestHistory(user_id);
        status = userDao.getUserStatus(user_id);
        vaccine = userDao.getUserVaccine(user_id);
    }

    public void insert(History history) {
//        HistoryRepository repository = null;
        repository.insert(history);
    }
//    public void getRiskStatus(int user_id){
//        userDao.getUserStatus(user_id);
//    }

    public LiveData<Integer> getUserStatus(Integer id) {
        return userDao.getUserStatus(id);
    }

    public LiveData<Integer> getUserVaccine(Integer id) {
        return userDao.getUserVaccine(id);
    }

    // pass the allHistory list to the activity that invokes it
//    public LiveData<List<History>> getAllHistory() {
//        return allHistory;
//    }

    public LiveData<List<History>> getLatestHistory(Integer id) {
        return repository.getLatestHistory(id);
    }

//    public LiveData<List<UserEntity>> getUserStatus(Integer id) {
//        return userDao.getUserStatus(id);
//    }
}