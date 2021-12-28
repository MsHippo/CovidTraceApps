package com.coffee.covidtrace.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import kotlin.jvm.JvmOverloads;

@Dao
public interface ThingsAnnouncementDao {

    //Select all items
    @Query("SELECT * FROM thingToDo_table " +
            "ORDER BY id DESC")
    LiveData<List<ThingsAnnouncement>> getAllAnnouncent();

}
