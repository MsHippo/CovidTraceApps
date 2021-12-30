package com.coffee.covidtrace.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import kotlin.jvm.JvmOverloads;

@Dao
public interface NotificationDao {

    //Select all items
    @JvmOverloads
    @Query("SELECT * FROM noti_table " +
            "WHERE noti_table.user_id == :id " +
            "ORDER BY id DESC")
    LiveData<List<NotificationEntity>> getAllNotification(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(NotificationEntity notificationEntity);

}
