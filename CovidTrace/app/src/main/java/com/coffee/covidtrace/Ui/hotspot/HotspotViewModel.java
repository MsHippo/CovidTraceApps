package com.coffee.covidtrace.Ui.hotspot;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Data.HotSpot;
import com.coffee.covidtrace.Repository.HotspotRepository;

import java.util.List;

public class HotspotViewModel  extends AndroidViewModel {
    private HotspotRepository hotspotRepository;
    private LiveData<List<HotSpot>> mAllHotspot;
    double lat, log;

    public HotspotViewModel(@NonNull Application application) {
        super(application);
        hotspotRepository = new HotspotRepository(application);
        mAllHotspot = hotspotRepository.getAllHotSpot(lat, log);
    }

    public LiveData<List<HotSpot>> getmAllHotspot(double lat, double log) {
        return hotspotRepository.getAllHotSpot(lat, log);
    }
}
