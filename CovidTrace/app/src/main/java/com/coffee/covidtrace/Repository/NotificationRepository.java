package com.coffee.covidtrace.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.NotificationEntity;
import com.coffee.covidtrace.Data.NotificationDao;

import java.util.List;

public class NotificationRepository {

    private NotificationDao mNotificationDao;
    private LiveData<List<NotificationEntity>> mAllNotification;
    private Database db;
    int user_id;


    public NotificationRepository(Application application) {
        db = Database.getDatabase(application);
        mAllNotification = mNotificationDao.getAllNotification(user_id);
    }

    public LiveData<List<NotificationEntity>> getAllNotification(int id) {
        return mNotificationDao.getAllNotification(id);
    }

    public void insert(NotificationEntity notificationEntity) {
        Database.databaseWriteExecutor.execute(() -> {
            mNotificationDao.insert(notificationEntity);
        });
    }
}
