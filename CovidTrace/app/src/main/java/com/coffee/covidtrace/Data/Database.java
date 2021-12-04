package com.coffee.covidtrace.Data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {UserEntity.class}, version = 1)
public abstract class Database extends RoomDatabase{

    private static final String dbName = "covidTrace_db";
    private static Database database;
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
