package com.coffee.covidtrace.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HotSpotDao {

    //Select all items
    @Query("SELECT * FROM hotspot_table " +
            "WHERE latitude=(:latitude) OR logitude=(:longitude)")
    LiveData<List<HotSpot>> getAllHotSpot(double latitude, double longitude);

}
