package com.example.git_set_code.cache;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.git_set_code.typeConverters.FloatTypeConverters;
import com.example.git_set_code.typeConverters.IntTypeConverters;
import com.example.git_set_code.typeConverters.StringTypeConverter;
import com.example.git_set_code.viewmodels.TripsData;

import java.util.ArrayList;


@Database(entities = {TripsDataRoom.class}, version = 1)
@TypeConverters({StringTypeConverter.class, IntTypeConverters.class, FloatTypeConverters.class})
public abstract class TripsDatabase extends RoomDatabase {
    private static TripsDatabase instance;

    public abstract TripsDataDao tripsDataDao();

    public static synchronized TripsDatabase getInstance(Context context){
        if(instance==null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TripsDatabase.class, "saved_data").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private TripsDataDao tripsDataDao;
        private TripsData tripsData;
        private  PopulateDbAsyncTask(TripsDatabase db){
            tripsDataDao = db.tripsDataDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tripsDataDao.insert(new TripsDataRoom(
                    0,
                    "NA",
                    "NA",
                    new ArrayList<String>(),
                    new ArrayList<String>(),
                    new ArrayList<Integer>(),
                    new ArrayList<String>(),
                    new ArrayList<String>(),
                    new ArrayList<Integer>(),
                    new ArrayList<String>(),
                    new ArrayList<String>(),
                    new ArrayList<Integer>(),
                    new ArrayList<String>(),
                    new ArrayList<String>(),
                    new ArrayList<Integer>(),
                    new ArrayList<String>(),
                    new ArrayList<Double>(),
                    new ArrayList<Double>(),
                    new ArrayList<String>() ,
                    new  ArrayList<String>() ,
                    new  ArrayList<String>()  ,
                    new  ArrayList<String>()  ,
                    new  ArrayList<String>()  ,
                    new  ArrayList<String>()  ,
                    new  ArrayList<String>()  ,
                    new  ArrayList<String>()  ,
                    new ArrayList<Integer>()  ,
                    new  ArrayList<Integer>() ,
                    new   ArrayList<Integer>() ,
                    new  ArrayList<Integer>()  ,
                    new  ArrayList<String>()  ,
                    new  ArrayList<String>()  ,
                    new   ArrayList<Integer>() ,
                    new   ArrayList<String>() ,
                    new  ArrayList<String>()  ,
                    0));
            return null;
        }

    }
}
