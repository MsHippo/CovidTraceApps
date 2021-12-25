package com.coffee.covidtrace.Ui.home.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.ThingsAnnouncement;
import com.coffee.covidtrace.Repository.HistoryRepository;
import com.coffee.covidtrace.Repository.ThingsAnnouncementRepository;

import java.util.List;

public class ImpNotesViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private ThingsAnnouncementRepository repository;
    private LiveData<List<ThingsAnnouncement>> allAccouncement;

    public ImpNotesViewModel(@NonNull Application application) {
        super(application);
        repository = new ThingsAnnouncementRepository(application);
        allAccouncement = repository.getAllAnnouncement();
    }

//    public ImpNotesViewModel() {
//
//    }

    // pass the allHistory list to the activity that invokes it
    public LiveData<List<ThingsAnnouncement>> getAllAnnouncement() {
        return allAccouncement;
    }
}