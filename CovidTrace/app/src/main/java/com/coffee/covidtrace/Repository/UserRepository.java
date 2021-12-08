//package com.coffee.covidtrace.Repository;
//
//import android.app.Application;
//
//import androidx.lifecycle.LiveData;
//
//import com.coffee.covidtrace.Data.Database;
//import com.coffee.covidtrace.Data.UserDao;
//import com.coffee.covidtrace.Data.UserEntity;
//
//import java.util.List;
//
//public class UserRepository {
//
//    private UserDao mUserDao;
//    private LiveData<List<UserEntity>> mAllUser;
//    private Database db;
//
//
//    public UserRepository(Application application) {
//        db = Database.getUserDatabase(application);
//        mUserDao = db.userDao();
//        mAllUser = mUserDao.registerUser();
//    }
//
//    public LiveData<List<UserEntity>> getAllHistory() {
//        return mAllUser;
//    }
//
//    public void insert(UserEntity userEntity) {
//        Database.databaseWriteExecutor.execute(() -> {
//        });
//    }
//}
