package com.coffee.covidtrace.Ui.healthAssessment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.UserDao;
import com.coffee.covidtrace.Data.UserEntity;

public class SubmitViewModel extends AndroidViewModel {
    Database database = Database.getDatabase(getApplication());
    UserDao userDao = database.userDao();


    public SubmitViewModel(@NonNull Application application) {
        super(application);
    }

    public void insert(int status, int user_id) {
//        HistoryRepository repository = null;
        userDao.updateRiskStatus(status, user_id);
    }
}
