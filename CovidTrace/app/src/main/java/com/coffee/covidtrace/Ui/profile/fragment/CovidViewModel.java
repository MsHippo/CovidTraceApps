package com.coffee.covidtrace.Ui.profile.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.UserDao;

public class CovidViewModel extends AndroidViewModel {
    Database database = Database.getDatabase(getApplication());
    UserDao userDao = database.userDao();
    private LiveData<Integer> status;
    private LiveData<Integer> vaccine;

    //    UserEntity userEntity;
    int user_id;

    public CovidViewModel(@NonNull Application application) {
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