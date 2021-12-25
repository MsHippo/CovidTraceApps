package com.coffee.covidtrace.Data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {
        UserEntity.class,
        History.class,
        ThingsAnnouncement.class},
        version = 7)

public abstract class Database extends RoomDatabase{

    private static final String dbName = "covidTrace_db";
    private static Database database;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized Database getDatabase(Context context){

        if(database == null){
            database = Room.databaseBuilder(context, Database.class, dbName)
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return database;
    }

    public abstract UserDao userDao();
    public abstract HistoryDao historyDao();
    public abstract ThingsAnnouncementDao thingsAnnouncementDao();

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                HistoryDao dao = database.historyDao();
                dao.deleteAll();

                // Seeding code
                History history = new History("Ulu Sungai Merah","2021-12-01", 1, "00:/00:/00");
                dao.insert(history);
                History history2 = new History("Jalan Fatimah","2021-12-01", 1, "00:/00:/00");
                dao.insert(history2);
            });
        }
    };

}
