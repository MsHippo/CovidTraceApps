package com.coffee.covidtrace.Ui;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.UserDao;
import com.coffee.covidtrace.Data.UserEntity;

public class UserRepository {
    private UserDao userDao;
    private UserEntity userEntityLiveData;
    String email, password;

    public UserRepository(Application application){
        Database database = Database.getUserDatabase(application);
        userDao = database.userDao();
        userEntityLiveData = userDao.login(email, password);
    }


}
