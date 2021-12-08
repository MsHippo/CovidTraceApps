package com.coffee.covidtrace.Data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {UserEntity.class}, version = 1)
public abstract class Database extends RoomDatabase{

    private static final String dbName = "covidTrace_db";
    private static Database database;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized Database getUserDatabase(Context context){

        if(database == null){
            database = Room.databaseBuilder(context, Database.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract UserDao userDao();
}
