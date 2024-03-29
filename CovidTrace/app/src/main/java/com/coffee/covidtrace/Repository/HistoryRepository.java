package com.coffee.covidtrace.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.HistoryDao;

import java.util.List;

public class HistoryRepository {

    private HistoryDao mHistoryDao;
    private LiveData<List<History>> mAllHistory;
    private LiveData<List<History>> mLatestHistory;
    private Database db;
//    History history;
    int user_id;


    public HistoryRepository(Application application) {
        db = Database.getDatabase(application);
        mHistoryDao = db.historyDao();
        mAllHistory = mHistoryDao.getAllHistory(user_id);
        mLatestHistory = mHistoryDao.getLatestHistory(user_id);
    }

    public LiveData<List<History>> getAllHistory(int id) {
        return mHistoryDao.getAllHistory(id);
    }
    public LiveData<List<History>> getLatestHistory(int id) {
        return mHistoryDao.getLatestHistory(id);
    }

    public void insert(History history) {
        Database.databaseWriteExecutor.execute(() -> {
            mHistoryDao.insert(history);
        });
    }

}
