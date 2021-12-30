package com.coffee.covidtrace.Ui.record;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.UserDao;
import com.coffee.covidtrace.Repository.HistoryRepository;

import java.util.List;

public class SuccessCheckViewModel extends AndroidViewModel {
    private HistoryRepository repository;
    Database database = Database.getDatabase(getApplication());
    UserDao userDao = database.userDao();
    private LiveData<Integer>status;
    private LiveData<Integer>vaccine;
    int user_id;

    public SuccessCheckViewModel(@NonNull Application application) {
        super(application);
        status = userDao.getUserStatus(user_id);
        vaccine = userDao.getUserVaccine(user_id);
    }

    public LiveData<Integer> getUserStatus(Integer id) {
        return userDao.getUserStatus(id);
    }

    public LiveData<Integer> getUserVaccine(Integer id) {
        return userDao.getUserVaccine(id);
    }
}
