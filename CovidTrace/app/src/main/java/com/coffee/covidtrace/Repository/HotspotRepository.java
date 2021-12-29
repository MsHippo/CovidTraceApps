package com.coffee.covidtrace.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.HotSpot;
import com.coffee.covidtrace.Data.HotSpotDao;

import java.util.List;

public class HotspotRepository {

    private HotSpotDao hotSpotDao;
    private LiveData<List<HotSpot>> mAllHotSpot;
    private Database db;
    double lat, log;

    public HotspotRepository(Application application) {
        db = Database.getDatabase(application);
        hotSpotDao = db.hotSpotDao();
        mAllHotSpot = hotSpotDao.getAllHotSpot(lat, log);
    }

    public LiveData<List<HotSpot>> getAllHotSpot(double lat, double log){
//        return thingsAnnouncementDao.getAllAnnouncent();
        return hotSpotDao.getAllHotSpot(lat, log);
    }
}
