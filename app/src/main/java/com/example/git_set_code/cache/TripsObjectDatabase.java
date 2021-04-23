package com.example.git_set_code.cache;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static java.time.chrono.IsoChronology.INSTANCE;

@Database(entities = {TripsObject.class}, version = 1)
public abstract class TripsObjectDatabase extends RoomDatabase {

    private static TripsObjectDatabase instance;

    public abstract TripsObjectDao tripsObjectDao();

    public static TripsObjectDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (TripsObjectDatabase.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        TripsObjectDatabase.class, "saved_data").fallbackToDestructiveMigration().build();
            }
        }
        return instance;
    }
}
