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
    private Database db;


    public HistoryRepository(Application application) {
        db = Database.getDatabase(application);
        mHistoryDao = db.historyDao();
        mAllHistory = mHistoryDao.getAllHistory();
    }

    public LiveData<List<History>> getAllHistory() {
        return mAllHistory;
    }

    public void insert(History history) {
        Database.databaseWriteExecutor.execute(() -> {
            mHistoryDao.insert(history);
        });
    }

}
