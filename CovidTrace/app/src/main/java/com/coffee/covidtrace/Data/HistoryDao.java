package com.coffee.covidtrace.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import kotlin.jvm.JvmOverloads;

@Dao
public interface HistoryDao {

    //Select all items
    @JvmOverloads
    @Query("SELECT * FROM history_table " +
            "WHERE history_table.user_id == :id " +
            "ORDER BY id DESC")
    LiveData<List<History>> getAllHistory(int id);

//    @Query("SELECT * FROM history_table" +
//            " WHERE history_table.user_id == :id " +
//            "ORDER BY id DESC")
//    LiveData<History> getHistory(int id);

    @Query("SELECT * FROM history_table " +
            "WHERE id = (SELECT MAX(id) FROM history_table) AND history_table.user_id == :id")
    LiveData<List<History>> getLatestHistory(int id);

    @Query("DELETE FROM history_table")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(History history);
}
