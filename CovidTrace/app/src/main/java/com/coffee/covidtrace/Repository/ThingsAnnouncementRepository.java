package com.coffee.covidtrace.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.ThingsAnnouncement;
import com.coffee.covidtrace.Data.ThingsAnnouncementDao;

import java.util.List;

public class ThingsAnnouncementRepository {

    private ThingsAnnouncementDao thingsAnnouncementDao;
    private LiveData<List<ThingsAnnouncement>> mAllAnnouncement;
    private Database db;

    public ThingsAnnouncementRepository(Application application) {
        db = Database.getDatabase(application);
        thingsAnnouncementDao = db.thingsAnnouncementDao();
        mAllAnnouncement = thingsAnnouncementDao.getAllAnnouncent();
    }

    public LiveData<List<ThingsAnnouncement>> getAllAnnouncement(){
//        return thingsAnnouncementDao.getAllAnnouncent();
        return mAllAnnouncement;
    }
}
