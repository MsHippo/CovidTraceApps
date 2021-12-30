package com.coffee.covidtrace.Ui.notification;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.coffee.covidtrace.Data.NotificationEntity;
import com.coffee.covidtrace.Repository.NotificationRepository;

import java.util.List;

public class NotificationViewModel extends ViewModel {

    private NotificationRepository repository;
    private LiveData<List<NotificationEntity>> allNotification;
    int user_id ;


    public NotificationViewModel(@NonNull Application application) {

        repository = new NotificationRepository(application);
        allNotification = repository.getAllNotification(user_id);
    }

    // pass the allHistory list to the activity that invokes it
    public LiveData<List<NotificationEntity>> getAllNotification(int id) {
        return repository.getAllNotification(id);
    }

}
