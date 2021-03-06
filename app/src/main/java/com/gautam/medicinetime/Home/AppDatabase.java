package com.gautam.medicinetime.Home;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Patient.class} , version = 10)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PatientDao userDao();

    private static AppDatabase appDatabase;

    public AppDatabase() {
    }

    public static synchronized AppDatabase getInstance(Context context){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context , AppDatabase.class, "AppDatabase")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return appDatabase;
    }


}
