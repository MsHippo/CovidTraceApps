package com.coffee.covidtrace.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.UserDao;
import com.coffee.covidtrace.Data.UserEntity;

import java.util.List;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<UserEntity>> mAllUser;
    private Database db;
    UserEntity user;


    public UserRepository(Application application) {
        db = Database.getDatabase(application);
        mUserDao = db.userDao();
        mAllUser = mUserDao.getAllUsers();
        mUserDao.registerUser(user);

    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return mAllUser;
    }

//    public void insert(UserEntity userEntity) {
//        Database.databaseWriteExecutor.execute(() -> {
//        });
//    }
}
