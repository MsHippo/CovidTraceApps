package com.coffee.covidtrace.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {

    //Select all items
    @Query("select * from history_table ORDER BY id DESC")
    LiveData<List<History>> getAllHistory();

    @Query("DELETE FROM history_table")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(History history);
}
