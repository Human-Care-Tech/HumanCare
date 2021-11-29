package com.gautam.medicinetime.Home;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Patient.class} , version = 6)
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

    static final Migration MIGRATION_1_6 = new Migration(1, 6) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users "
                    +"ADD COLUMN address TEXT");

        }
    };
}
